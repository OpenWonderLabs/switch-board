package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.Red
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mikepenz.iconics.compose.IconicsPainter
import com.mikepenz.iconics.typeface.library.googlematerial.GoogleMaterial
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.Consts
import com.theswitchbot.switchboard.vm.DeviceViewModel


enum class MenuItem {
    Dashboard, SceneList, SystemSettings
}

@Composable
fun MainScreen(viewModel: DeviceViewModel) {
    var selectedMenu by remember { mutableStateOf(MenuItem.Dashboard) }

    Row(modifier = Modifier
        .fillMaxSize()
        .background(Consts.mainBg)) {
        MenuColumn(selectedMenu) { selectedMenu = it }
        ContentColumn(selectedMenu,viewModel)
    }
}

@Composable
fun MenuColumn(selectedMenu: MenuItem, onMenuSelected: (MenuItem) -> Unit) {
    Column(
        modifier = Modifier
            .width(250.dp)
            .fillMaxHeight()
            .background(Consts.menuBg)
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))
        Image(
            painter = painterResource(id = R.mipmap.dashboard),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(80.dp)
                .clip(RoundedCornerShape(40.dp))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "SwitchBot", color = White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(16.dp))
        Divider(color = LightGray, thickness = 1.dp)
        Spacer(modifier = Modifier.height(16.dp))
        MenuButton(
            icon = GoogleMaterial.Icon.gmd_home,
            text = stringResource(R.string.menu_dashboard),
            isSelected = selectedMenu == MenuItem.Dashboard,
            onClick = { onMenuSelected(MenuItem.Dashboard) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MenuButton(
            icon = GoogleMaterial.Icon.gmd_ballot,
            text = stringResource(R.string.menu_scene_list),
            isSelected = selectedMenu == MenuItem.SceneList,
            onClick = { onMenuSelected(MenuItem.SceneList) }
        )
        Spacer(modifier = Modifier.height(8.dp))
        MenuButton(
            icon = GoogleMaterial.Icon.gmd_settings,
            text = stringResource(R.string.menu_system_settings),
            isSelected = selectedMenu == MenuItem.SystemSettings,
            onClick = { onMenuSelected(MenuItem.SystemSettings) }
        )

        Spacer(modifier = Modifier.weight(1f))

        Divider(color = LightGray, thickness = 1.dp)
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(vertical = 16.dp)
                .clickable { /* 切换语言逻辑 */ }
        ) {
            Text(text = "❤️SwitchBot", color = White, fontSize = 16.sp)
        }
    }
}


@Composable
fun MenuButton(icon: GoogleMaterial.Icon, text: String, isSelected: Boolean, onClick: () -> Unit) {
    val backgroundColor = if (isSelected) Color(0xFF3C3C3C) else Color.Transparent
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor, RoundedCornerShape(8.dp))
            .padding(8.dp)
            .clickable(onClick = onClick)
    ) {
        Image(
            painter= IconicsPainter(icon),
            colorFilter = ColorFilter.tint(White),
            contentDescription = null,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Text(text = text, color = White, fontSize = 16.sp)
    }
}

@Composable
fun ContentColumn(selectedMenu: MenuItem,viewModel: DeviceViewModel) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)

    ) {
        when (selectedMenu) {
            MenuItem.Dashboard -> DashboardContent(viewModel)
            MenuItem.SceneList -> SceneListContent(viewModel)
            MenuItem.SystemSettings -> SystemSettingsContent(viewModel)
        }
    }
}

@Composable
fun DashboardContent(viewModel: DeviceViewModel) {
    viewModel.requestDevices()
    DeviceList(viewModel = viewModel)
}

@Composable
fun SceneListContent(viewModel: DeviceViewModel) {
    viewModel.requestScenes()
    SceneList(viewModel = viewModel)
}

@Composable
fun SystemSettingsContent(viewModel: DeviceViewModel) {
    SystemSettings(viewModel = viewModel)
}