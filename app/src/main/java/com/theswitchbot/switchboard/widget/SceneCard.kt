package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.Bot
import com.theswitchbot.switchboard.entity.BotCmd
import com.theswitchbot.switchboard.entity.BotStatus
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.util.CardHelper
import com.theswitchbot.switchboard.entity.DeviceType
import com.theswitchbot.switchboard.entity.EmptyStatus
import com.theswitchbot.switchboard.entity.FailedStatus
import com.theswitchbot.switchboard.entity.Scene
import com.theswitchbot.switchboard.ui.theme.PurpleGrey40
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.component.CircularButton
import kotlin.random.Random


@Composable
fun SceneCard(scene: Scene, viewModel: DeviceViewModel) {
    val loadingStatus=viewModel.cmdStatus(scene.sceneId).collectAsState()
    Box(
        modifier = Modifier
            .wrapContentWidth()
            .wrapContentHeight()
    ) {
        Card(
            modifier = Modifier
                .padding(16.dp)
                .size(200.dp, 60.dp).clickable(onClick = {
                    viewModel.execScene(scene.sceneId)
                }),
            colors = CardDefaults.cardColors(
                containerColor = DeviceType.colors[Random.nextInt(0, 13)]
            ),
            shape = RoundedCornerShape(16.dp),
            elevation = CardDefaults.cardElevation(),
        ) {
            Row(
                modifier = Modifier.fillMaxWidth().fillMaxHeight().padding(8.dp),
                horizontalArrangement = Arrangement.Start,
                verticalAlignment = Alignment.CenterVertically
            )  {
                Image(
                    IconicsPainter(GoogleMaterial.Icon.gmd_auto_awesome),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(White),
                    modifier = Modifier.size(30.dp)
                )
                Text(
                    text = scene.sceneName,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = Size.cardTitle,
                    maxLines = 1,
                    modifier = Modifier.padding(top = Size.cardMargin)
                )
            }
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
