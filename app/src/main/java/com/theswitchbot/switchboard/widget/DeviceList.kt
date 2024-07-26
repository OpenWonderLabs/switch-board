package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.entity.BatteryCircularFan
import com.theswitchbot.switchboard.entity.BlindTilt
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.Curtain
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.Humidifier
import com.theswitchbot.switchboard.entity.IRDevice
import com.theswitchbot.switchboard.entity.Light
import com.theswitchbot.switchboard.entity.Lock
import com.theswitchbot.switchboard.entity.Plug
import com.theswitchbot.switchboard.entity.RobotVacuumCleaner
import com.theswitchbot.switchboard.entity.Sensor
import com.theswitchbot.switchboard.vm.DeviceViewModel



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun DeviceList(viewModel: DeviceViewModel) {
    val deviceList=viewModel.deviceList.collectAsState()
    LazyColumn {
        item {
            FlowRow(modifier = Modifier.padding(8.dp)) {
                deviceList.value.forEach { device ->
                    when (device.deviceType) {
                        DeviceType.Bot -> BotCard(device = device as Bot, viewModel = viewModel)

                        DeviceType.Curtain3,
                        DeviceType.Curtain -> CurtainCard(device = device as Curtain, viewModel = viewModel)

                        DeviceType.Keypad, DeviceType.KeypadTouch,
                        DeviceType.HubPlus, DeviceType.HubMini,
                        DeviceType.Remote, DeviceType.IndoorCam,
                        DeviceType.PanTiltCam, DeviceType.PanTiltCam2K,
                        DeviceType.Hub -> DefaultCard(device = device, viewModel = viewModel)

                        DeviceType.Hub2,
                        DeviceType.Meter,
                        DeviceType.MeterPlus,
                        DeviceType.OutdoorMeter,
                         -> MeterCard(device = device, viewModel = viewModel)

                        DeviceType.Lock,
                        DeviceType.LockPro -> LockCard(device = device as Lock, viewModel = viewModel)

                        DeviceType.MotionSensor -> MotionSensorCard(device = device as Sensor, viewModel = viewModel)
                        DeviceType.ContactSensor -> ContactSensorCard(device = device as Sensor, viewModel = viewModel)
                        DeviceType.WaterLeakDetector -> WaterLeakCard(device = device as Sensor, viewModel = viewModel)

                        DeviceType.CeilingLight,DeviceType.StripLight,
                        DeviceType.ColorBulb, DeviceType.CeilingLightPro
                        -> LightCard(device = device as Light, viewModel = viewModel)

                        DeviceType.Plug -> PlugCard(device = device as Plug, viewModel = viewModel)

                        DeviceType.PlugMiniUS,
                        DeviceType.PlugMiniJP -> PlugMiniCard(device = device as Plug, viewModel = viewModel)

                        DeviceType.S1,
                        DeviceType.S1Plus,
                        DeviceType.S10 -> SweeperCard(device = device as RobotVacuumCleaner, viewModel = viewModel)

                        DeviceType.Humidifier -> HumidifierCard(device = device as Humidifier, viewModel = viewModel)
                        DeviceType.BlindTilt -> BlindTiltCard(device = device as BlindTilt, viewModel = viewModel)

                        DeviceType.BatteryFan -> FanCard(device = device as BatteryCircularFan, viewModel = viewModel)

                        DeviceType.IRRemote -> RemoteCard(device = device as IRDevice, viewModel = viewModel)

                        else -> DefaultCard(device = device, viewModel = viewModel)
                    }

                }
            }
        }
    }
}