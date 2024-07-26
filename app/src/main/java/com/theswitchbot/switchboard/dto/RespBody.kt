package com.theswitchbot.switchboard.dto


class RespBody<T>(val statusCode: Int, val body: T, val message: String){
    fun success()=statusCode==100
}