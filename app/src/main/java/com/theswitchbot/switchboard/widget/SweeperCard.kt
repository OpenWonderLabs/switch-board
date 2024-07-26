package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.MaterialTheme
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
import com.theswitchbot.switchboard.entity.RobotVacuumCleaner
import com.theswitchbot.switchboard.entity.RobotVacuumCleanerCmd
import com.theswitchbot.switchboard.entity.RobotVacuumCleanerStatus
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.vm.DeviceViewModel

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */



@Composable
fun SweeperCard(device: RobotVacuumCleaner, viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    var workingStatus by remember {
        mutableStateOf(
            if(status is RobotVacuumCleanerStatus){
                (status as RobotVacuumCleanerStatus).workingStatus
            }else{
                ""
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
                    color = Color.White,
                    maxLines = 1,
                    fontSize = Size.cardSubTitle
                )
            }

            ElevatedButton(
                modifier=Modifier
                    .width(100.dp).height(30.dp),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.tertiary),
                onClick = {
                    viewModel.sendCmd(device.deviceId, RobotVacuumCleanerCmd.dock){
                        if(it){
                            workingStatus=""
                        }
                    }
                }) {
                Text(RobotVacuumCleanerCmd.dock,color = Color.White,
                    fontSize=Size.cardDesc)
            }

            Row(
                modifier = Modifier.fillMaxWidth().height(30.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            )  {
                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.primary),
                    onClick = {
                        viewModel.sendCmd(device.deviceId, RobotVacuumCleanerCmd.start){
                            if(it){
                                workingStatus=""
                            }
                        }
                    }
                ) {
                    Text(RobotVacuumCleanerCmd.start,color = Color.White,
                        fontSize=Size.cardDesc)
                }

                ElevatedButton(
                    colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colorScheme.secondary),
                    onClick = {
                        viewModel.sendCmd(device.deviceId, RobotVacuumCleanerCmd.stop){
                            if(it){
                                workingStatus=""
                            }
                        }
                    }) {
                    Text(RobotVacuumCleanerCmd.stop,color = Color.White,
                        fontSize=Size.cardDesc)
                }


            }

        }
    }
}