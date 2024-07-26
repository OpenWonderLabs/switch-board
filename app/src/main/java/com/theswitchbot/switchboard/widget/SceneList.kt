package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.vm.DeviceViewModel

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SceneList(viewModel: DeviceViewModel) {
    val sceneList=viewModel.sceneList.collectAsState()
    LazyColumn {
        item {
            FlowRow(modifier = Modifier.padding(8.dp)) {
                sceneList.value.forEach { scene ->
                    SceneCard(scene = scene, viewModel = viewModel)
                }
            }
        }
    }
}