package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.entity.BaseDevice
import com.theswitchbot.switchboard.ui.theme.PurpleGrey40
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.vm.DeviceViewModel

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
@Composable
fun DeviceCard(device: BaseDevice,viewModel: DeviceViewModel, content:@Composable ColumnScope.() -> Unit) {
    val loadingStatus=viewModel.cmdStatus(device.deviceId).collectAsState()
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier
                .padding(6.dp)
                .size(CardHelper.cardWidth(device.deviceType), Size.cardHeight),
            colors = CardDefaults.cardColors(
                containerColor = CardHelper.cardBg(device.deviceType)
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(),
        ) {
            content()

        }
        if (loadingStatus.value) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.TopStart).padding(8.dp)
                    .size(25.dp),
                color = PurpleGrey40,
                strokeWidth = 4.dp
            )
        }

    }
}