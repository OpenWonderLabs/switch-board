package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Slider
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.entity.BaseDevice
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.entity.LightCmd
import com.theswitchbot.switchboard.entity.LightStatus
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.vm.DeviceViewModel

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
@Composable
fun LightCard(device: BaseDevice, viewModel: DeviceViewModel) {

    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var powerStatus by remember {
        mutableStateOf(
            if(status is LightStatus){
                (status as LightStatus).power
            }else{
                Consts.on
            }
        )
    }
    var brightness by remember {
        mutableStateOf(
            if(status is LightStatus){
                (status as LightStatus).brightness
            }else{
                0
            }
        )
    }
    DeviceCard(device,viewModel){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    painterResource(id = CardHelper.deviceIcon(device.deviceType)),
                    contentDescription = null,
                    modifier = Modifier.size(30.dp)
                )
                Switch(
                    modifier = Modifier.size(60.dp,30.dp),
                    checked = powerStatus== Consts.on,
                    onCheckedChange = {
                        powerStatus=if(it){
                            Consts.on
                        }else{
                            Consts.off
                        }
                        viewModel.sendCmd(device.deviceId, if(it){ LightCmd.turnOn }else{ LightCmd.turnOff }){ res->
                            if(!res){
                                powerStatus=if(it){
                                    Consts.off
                                }else{
                                    Consts.on
                                }
                            }
                        }
                    }
                )

            }


            Text(
                text = device.deviceName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = Size.cardTitle,
                maxLines = 1,
                modifier = Modifier.padding(top = Size.cardMargin)
            )
            Slider(
                valueRange = 0f..100f,
                value = brightness.toFloat(),
                onValueChange = {
                    brightness = it.toInt()
                },
                onValueChangeFinished = {
                    viewModel.sendCmd(device.deviceId, LightCmd.setBrightness, params = "${brightness}"){

                    }
                }
            )
        }
    }
}