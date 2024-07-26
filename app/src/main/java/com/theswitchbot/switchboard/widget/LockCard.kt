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
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.BotCmd
import com.theswitchbot.switchboard.entity.BotStatus
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.entity.Lock
import com.theswitchbot.switchboard.entity.LockCmd
import com.theswitchbot.switchboard.entity.LockStatus
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton


@Composable
fun LockCard(device:Lock,viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var lockState by remember {
        mutableStateOf(
            if(status is LockStatus){
                (status as LockStatus).lockState
            }else{
                Consts.unlock
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
                    fontSize = Size.cardDesc
                )
            }

            Text(
                text = stringResource(id = when (lockState) {
                    Consts.lock ->R.string.text_locked
                    Consts.unlock ->R.string.text_unlocked
                    Consts.jammed -> R.string.text_jammed
                    else -> R.string.text_empty
                }),
                color = White,
                fontWeight = FontWeight.Bold,
                fontSize = Size.cardTitle,
                maxLines = 1,
                modifier = Modifier.padding(top = Size.cardMargin)
            )

            Row(
                modifier = Modifier.fillMaxWidth().padding(top = Size.cardMargin),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )  {
                ElevatedButton(
                    modifier=Modifier
                        .width(75.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
                    onClick = { 
                        viewModel.sendCmd(device.deviceId,LockCmd.lock){
                            if(it){
                                lockState=Consts.lock
                            }
                        }
                    }
                ) {
                    Text(text=Consts.lock,
                            color = White,
                        fontSize=Size.cardDesc)
                }

                ElevatedButton(
                    modifier=Modifier
                        .width(85.dp),
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.secondary),
                    onClick = {
                        viewModel.sendCmd(device.deviceId,LockCmd.unlock){
                            if(it){
                                lockState=Consts.unlock
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
