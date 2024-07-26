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
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.BaseDevice
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.BotCmd
import com.theswitchbot.switchboard.entity.BotStatus
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.entity.Hub2Status
import com.theswitchbot.switchboard.entity.MeterStatus
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton


@Composable
fun MeterCard(device:BaseDevice,viewModel: DeviceViewModel) {
    val status by remember {
        viewModel.status(device.deviceId,device.deviceType)
    }
    val (temp,humi)=when(status){
        is MeterStatus->{
            val meterStatus=status as MeterStatus
            "${meterStatus.temperature}℃" to "${meterStatus.humidity}%"
        }
        is Hub2Status->{
            val hub2Status=status as Hub2Status
            "${hub2Status.temperature}℃" to "${hub2Status.humidity}%"
        }
        else->"--" to "--"
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
            Spacer(modifier = Modifier.height(8.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    painter= painterResource(R.mipmap.icon_16pt_temperature_default),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = temp,
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
                    painter= painterResource(R.mipmap.icon_16pt_humidity_default),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Text(
                    text = humi,
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
