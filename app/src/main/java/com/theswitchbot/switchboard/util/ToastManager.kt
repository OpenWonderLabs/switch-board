package com.theswitchbot.switchboard.util

import android.widget.Toast
import androidx.annotation.StringRes
import com.theswitchbot.switchboard.APP

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
object ToastManager {
    fun toast(text:String){
        Toast.makeText(APP.context,text,Toast.LENGTH_LONG).show()
    }
    fun toast(@StringRes textResId:Int){
        Toast.makeText(APP.context,textResId,Toast.LENGTH_LONG).show()
    }
}