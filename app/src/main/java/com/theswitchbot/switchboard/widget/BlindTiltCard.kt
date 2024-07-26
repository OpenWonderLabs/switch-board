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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.BlindTilt
import com.theswitchbot.switchboard.entity.BlindTiltStatus
import com.theswitchbot.switchboard.entity.Curtain
import com.theswitchbot.switchboard.entity.CurtainCmd
import com.theswitchbot.switchboard.entity.CurtainStatus
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.vm.DeviceViewModel


@Composable
fun BlindTiltCard(device:BlindTilt,viewModel: DeviceViewModel) {
    val percentFormat= stringResource(id = R.string.text_percent_format)
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var slidePosition by remember {
        mutableStateOf(
            if(status is BlindTiltStatus){
                (status as BlindTiltStatus).slidePosition
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
                Text(
                    text = when(status){
                        is EmptyStatus->{
                            stringResource(id = R.string.device_status_loading)
                        }
                        is FailedStatus->{
                            stringResource(id = R.string.device_status_failed)
                        }
                        is BlindTiltStatus->{
                            String.format(percentFormat,slidePosition)
                        }
                        else->""
                    },
                    color = White,
                    maxLines = 1,
                    fontSize = Size.cardDesc
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
                value = slidePosition.toFloat(),
                onValueChange = {
                    slidePosition = it.toInt()
                },
                onValueChangeFinished = {
                    viewModel.sendCmd(device.deviceId,CurtainCmd.setPosition, params = "0,ff,${slidePosition}"){

                    }
                }
            )
        }
    }
}