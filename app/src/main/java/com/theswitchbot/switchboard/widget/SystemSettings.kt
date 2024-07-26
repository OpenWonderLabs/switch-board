package com.theswitchbot.switchboard.widget

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.theswitchbot.switchboard.APP
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.util.SPUtil
import com.theswitchbot.switchboard.util.ToastManager
import com.theswitchbot.switchboard.vm.DeviceViewModel

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
@Composable
fun SystemSettings(viewModel: DeviceViewModel) {
    var tokenFiled by remember { mutableStateOf(TextFieldValue(SPUtil.getToken(APP.context))) }
    var secretField by remember { mutableStateOf(TextFieldValue(SPUtil.getSecret(APP.context))) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            value = tokenFiled,
            onValueChange = { tokenFiled = it },
            label = { Text("Token") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        TextField(
            value = secretField,
            onValueChange = { secretField = it },
            label = { Text("Secret") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        )

        Button(
            onClick = {
                SPUtil.saveToken(APP.context,tokenFiled.text)
                SPUtil.saveSecret(APP.context,secretField.text)
                ToastManager.toast(R.string.text_success)
            },
            modifier = Modifier.padding(vertical = 16.dp)
        ) {
            Text("OK", fontSize = 16.sp)
        }
    }
}