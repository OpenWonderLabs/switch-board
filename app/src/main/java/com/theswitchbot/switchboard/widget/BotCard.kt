package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
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
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.BotCmd
import com.theswitchbot.switchboard.entity.BotStatus
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton


@Composable
fun BotCard(device:Bot,viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var powerStatus by remember {
        mutableStateOf(
            if(status is BotStatus){
                (status as BotStatus).power
            }else{
                Consts.on
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
                if(status is BotStatus){
                    val botStatus=status as BotStatus
                    if(botStatus.deviceMode==Consts.pressMode){
                        CircularButton(onClick = {
                            viewModel.sendCmd(device.deviceId, BotCmd.press)
                        })
                    }else{
                        Switch(
                            modifier = Modifier.size(60.dp,30.dp),
                            checked = powerStatus==Consts.on,
                            onCheckedChange = {
                                botStatus.power=if(it){Consts.on}else{Consts.off}
                                powerStatus=botStatus.power
                                viewModel.sendCmd(device.deviceId,
                                    if(it){BotCmd.turnOn}else{BotCmd.turnOff}){res->
                                    if(!res){
                                        botStatus.power=if(it){
                                            Consts.off
                                        }else{
                                            Consts.on
                                        }
                                        powerStatus=botStatus.power
                                    }
                                }
                            }
                        )
                    }
                }
            }


            Text(
                text = device.deviceName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = Size.cardTitle,
                maxLines = 1,
                modifier = Modifier.padding(top = Size.cardMargin)
            )
            val statusText=when(status){
                is EmptyStatus->{
                    stringResource(id = R.string.device_status_loading)
                }
                is FailedStatus->{
                    stringResource(id = R.string.device_status_failed)
                }
                is BotStatus->{
                    val botStatus=status as BotStatus
                    when(botStatus.deviceMode){
                        Consts.pressMode->stringResource(id = R.string.text_press_mode)
                        Consts.switchMode->{
                            if(powerStatus==Consts.on){
                                stringResource(id = R.string.text_on)
                            }else{
                                stringResource(id = R.string.text_off)
                            }
                        }
                        Consts.customizeMode->stringResource(id = R.string.text_customize_mode)
                        else->""
                    }
                }
                else->""
            }
            Text(
                text = statusText,
                color = White,
                maxLines = 1,
                fontSize = Size.cardDesc
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SwitchBoardTheme {
        DefaultCard(Bot("","previce","Bot"),DeviceViewModel())
    }
}