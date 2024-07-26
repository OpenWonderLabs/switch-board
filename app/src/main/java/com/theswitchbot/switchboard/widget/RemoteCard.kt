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
import com.theswitchbot.switchboard.entity.IRCmd
import com.theswitchbot.switchboard.entity.IRDevice
import com.theswitchbot.switchboard.entity.Lock
import com.theswitchbot.switchboard.entity.LockCmd
import com.theswitchbot.switchboard.entity.LockStatus
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton


@Composable
fun RemoteCard(device:IRDevice,viewModel: DeviceViewModel) {
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

            if(device.remoteType!=Consts.others) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
                        onClick = {
                            viewModel.sendCmd(device.deviceId, IRCmd.turnOn)
                        }
                    ) {
                        Text(text=Consts.on,
                                color = White,
                            fontSize=Size.cardDesc)
                    }

                    ElevatedButton(
                        colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.secondary),
                        onClick = {
                            viewModel.sendCmd(device.deviceId, IRCmd.turnOff)
                        }) {
                        Text(
                            text= Consts.off,
                            color = White,
                            fontSize=Size.cardDesc
                        )
                    }
                }
            }else{
                Text(
                    text = stringResource(id = R.string.text_unsupported),
                    color = White,
                    maxLines = 1,
                    fontSize = Size.cardSubTitle
                )
            }
        }
    }
}
