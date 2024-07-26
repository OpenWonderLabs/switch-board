package com.theswitchbot.switchboard.util

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences


object SPUtil {
    private const val tokenKey="token"
    private const val secretKey="secret"

    fun getToken(context:Context):String{
        return getSp(context).getString(tokenKey,"")!!
    }

    fun getSecret(context:Context):String{
        return getSp(context).getString(secretKey,"")!!
    }

    fun saveToken(context: Context,token:String){
        val sp= getSp(context)
        val editor=sp.edit()
        editor.putString(tokenKey,token)
        editor.commit()
    }

    fun saveSecret(context: Context,secret:String){
        val sp= getSp(context)
        val editor=sp.edit()
        editor.putString(secretKey,secret)
        editor.commit()
    }


    private fun getSp(context: Context): SharedPreferences {
        return context.getSharedPreferences("shard_data", MODE_PRIVATE)
    }
}