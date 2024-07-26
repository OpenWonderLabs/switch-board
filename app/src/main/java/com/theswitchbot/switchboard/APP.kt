package com.theswitchbot.switchboard

import android.app.Application
import android.content.Context

/**
author : zhouyufei
date   : 2024/07/23
desc   :
 */
class APP:Application() {
    companion object{
        lateinit var context: Context
            private set
    }


    override fun onCreate() {
        super.onCreate()
        context=this
    }
}