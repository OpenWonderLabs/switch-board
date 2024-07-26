package com.theswitchbot.switchboard.widget.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.ui.theme.Purple80

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
@Composable
fun CircularButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(Purple80)
            .clickable { onClick() }
    )
}

@Composable
@Preview
fun previewCircularButton(){
    CircularButton{

    }
}