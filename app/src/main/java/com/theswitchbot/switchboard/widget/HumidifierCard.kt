package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.BatteryCircularFan
import com.theswitchbot.switchboard.entity.BatteryCircularFanCmd
import com.theswitchbot.switchboard.entity.BatteryCircularFanStatus
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.BotCmd
import com.theswitchbot.switchboard.entity.BotStatus
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.entity.Humidifier
import com.theswitchbot.switchboard.entity.HumidifierCmd
import com.theswitchbot.switchboard.entity.HumidifierStatus
import com.theswitchbot.switchboard.entity.Lock
import com.theswitchbot.switchboard.entity.LockCmd
import com.theswitchbot.switchboard.entity.LockStatus
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton


@Composable
fun HumidifierCard(device:Humidifier,viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var power by remember {
        mutableStateOf(
            if(status is HumidifierStatus){
                (status as HumidifierStatus).power
            }else{
                Consts.off
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
                    text = device.deviceName,
                    color = White,
                    maxLines = 1,
                    fontSize = Size.cardSubTitle
                )
            }

            Text(
                text = power,
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = Size.cardTitle,
                maxLines = 1,
                modifier = Modifier.padding(top = Size.cardMargin)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )  {
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
                    onClick = { 
                        viewModel.sendCmd(device.deviceId,HumidifierCmd.turnOn){
                            if(it){
                                power=Consts.on
                            }
                        }
                    }
                ) {
                    Text(text=Consts.lock,
                            color = White,
                        fontSize=Size.cardDesc)
                }

                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.secondary),
                    onClick = {
                        viewModel.sendCmd(device.deviceId,HumidifierCmd.turnOff){
                            if(it){
                                power=Consts.off
                            }
                        }
                    }) {
                    Text(text=Consts.unlock,
                        color = White,
                        fontSize=Size.cardDesc)
                }
            }
        }
    }
}
