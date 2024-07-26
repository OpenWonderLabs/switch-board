package com.theswitchbot.switchboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.core.view.WindowCompat
import com.theswitchbot.switchboard.ui.theme.SwitchBoardTheme
import com.theswitchbot.switchboard.vm.DeviceViewModel
import com.theswitchbot.switchboard.widget.MainScreen

class MainActivity : ComponentActivity() {
    private val viewModel: DeviceViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
            SwitchBoardTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    MainScreen(viewModel)
                }
            }
        }
    }
}