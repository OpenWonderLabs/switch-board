package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Blue
import androidx.compose.ui.graphics.Color.Companion.Green
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.ContactSensorStatus
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.Hub2Status
import com.theswitchbot.switchboard.entity.MotionSensorStatus
import com.theswitchbot.switchboard.entity.Sensor
import com.theswitchbot.switchboard.vm.DeviceViewModel


@Composable
fun ContactSensorCard(device:Sensor,viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    val (moveDetected,brightness,openState)=when(status){
        is ContactSensorStatus ->{
            val mStatus=status as ContactSensorStatus
            Triple(mStatus.moveDetected, mStatus.brightness,mStatus.openState)
        }
        else->Triple(false, "--","--")
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    painter= IconicsPainter(GoogleMaterial.Icon.gmd_directions_run),
                    colorFilter = ColorFilter.tint(Green),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = stringResource(id = if(moveDetected){
                        R.string.text_move_detected
                    }else{
                        R.string.text_move_undetected
                    }),
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = Size.cardSubTitle,
                    maxLines = 1,
                    modifier = Modifier.padding(top = Size.cardMargin)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    painter= IconicsPainter(GoogleMaterial.Icon.gmd_sensor_door),
                    colorFilter = ColorFilter.tint(Blue),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = openState,
                    color = White,
                    fontWeight = FontWeight.Bold,
                    fontSize = Size.cardSubTitle,
                    maxLines = 1,
                    modifier = Modifier.padding(top = Size.cardMargin)
                )
            }
        }
    }
}

