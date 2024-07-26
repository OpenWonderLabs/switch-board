package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.BaseDevice
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel


@Composable
fun DefaultCard(device: BaseDevice, viewModel: DeviceViewModel) {
    DeviceCard(device = device, viewModel = viewModel) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.Start
        ) {
            Image(
                painterResource(id = CardHelper.deviceIcon(device.deviceType)),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = device.deviceName,
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = Size.cardTitle,
                maxLines = 1,
                modifier = Modifier.padding(top = Size.cardMargin)
            )
        }
    }
}
@Preview(showBackground = true)
@Composable
fun DefaultCardPreview() {
    SwitchBoardTheme {
        DefaultCard(Bot("","preview","Bot"), DeviceViewModel())
    }
}