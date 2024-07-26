package com.theswitchbot.switchboard.vm

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.dto.DeviceList
import com.theswitchbot.switchboard.networking.APIUrl
import com.theswitchbot.switchboard.networking.AuthPlugin
import com.theswitchbot.switchboard.dto.RespBody
import com.theswitchbot.switchboard.entity.BaseDevice
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.entity.Scene
import com.theswitchbot.switchboard.entity.Status
import com.theswitchbot.switchboard.entity.createDevice
import com.theswitchbot.switchboard.entity.createStatus
import com.theswitchbot.switchboard.util.ToastManager
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType
import io.ktor.serialization.gson.gson
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import java.util.concurrent.ConcurrentHashMap


class DeviceViewModel : ViewModel() {

    var deviceList = MutableStateFlow<List<BaseDevice>>(emptyList())
    var sceneList = MutableStateFlow<List<Scene>>(emptyList())

    private val statusMap = ConcurrentHashMap<String, MutableState<Status>>()
    private val cmdStatusMap = ConcurrentHashMap<String, MutableStateFlow<Boolean>>()

    val apiClient = HttpClient(CIO) {
        install(AuthPlugin)
        install(Logging) {
            level = LogLevel.BODY

        }
        install(ContentNegotiation) {
            gson()
        }
    }

    fun status(deviceId: String, deviceType: String): MutableState<Status> {
        return if (statusMap.containsKey(deviceId)) {
            statusMap[deviceId]!!
        } else {
            val status = statusMap.getOrPut(deviceId) {
                mutableStateOf(EmptyStatus(deviceId))
            }
            viewModelScope.launch {
                val resp: RespBody<Map<String, Any>>? = try {
                    apiClient.get(APIUrl.status(deviceId)).body()

                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
                if (resp?.success() == true) {
                    status.value = createStatus(deviceType, resp.body)
                } else {
                    status.value = FailedStatus(deviceId)
                }
            }
            status
        }
    }

    fun cmdStatus(deviceId: String): MutableStateFlow<Boolean> {
        return cmdStatusMap.getOrPut(deviceId) {
            MutableStateFlow(false)
        }
    }

    fun sendCmd(
        deviceId: String,
        cmd: String,
        commandType: String = Consts.command,
        params: Any = Consts.default,
        listener: (success: Boolean) -> Unit = {}
    ) {
        val status = cmdStatus(deviceId)
        viewModelScope.launch {
            status.value = true
            val resp: RespBody<DeviceList>? = try {
                apiClient.post(APIUrl.commands(deviceId)) {
                    contentType(ContentType.Application.Json)
                    setBody(
                        mapOf(
                            Consts.command to cmd,
                            Consts.commandType to commandType,
                            Consts.parameter to params
                        )
                    )
                }.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }

            if (resp?.success() == true) {
                ToastManager.toast(R.string.text_success)
                listener(true)
            } else {
                ToastManager.toast(R.string.text_failed)
                listener(false)
            }
            status.value = false
        }
    }


    fun requestDevices() {
        viewModelScope.launch {
            val resp: RespBody<DeviceList>? = try {
                apiClient.get(APIUrl.devices).body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            statusMap.clear()
            if (resp?.success() == true) {
                val list = ArrayList<BaseDevice>()
                resp.body.deviceList.forEach {
                    list.add(createDevice(false, it))
                }
                resp.body.infraredRemoteList.forEach {
                    list.add(createDevice(true, it))
                }
                deviceList.value = list
            } else {
                deviceList.value = emptyList()
            }
        }
    }

    fun requestScenes() {
        viewModelScope.launch {
            val resp: RespBody<List<Scene>>? = try {
                apiClient.get(APIUrl.scenes).body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            if (resp?.success() == true) {
                sceneList.value = resp.body
            } else {
                deviceList.value = emptyList()
            }
        }
    }

    fun execScene(sceneId: String) {
        val status = cmdStatus(sceneId)
        viewModelScope.launch {
            status.value = true
            val resp: RespBody<DeviceList>? = try {
                apiClient.post(APIUrl.execScene(sceneId)) {
                    contentType(ContentType.Application.Json)
                }.body()
            } catch (e: Exception) {
                e.printStackTrace()
                null
            }
            if (resp?.success() == true) {
                ToastManager.toast(R.string.text_success)
            } else {
                ToastManager.toast(R.string.text_failed)
            }
            status.value = false
        }
    }


}