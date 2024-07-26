package com.theswitchbot.switchboard.networking


object APIUrl {
    const val host="https://api.switch-bot.com"

    //Get device list
    const val devices="${host}/v1.1/devices"

    //Get device status
    fun status(deviceId:String)="${host}/v1.1/devices/${deviceId}/status"

    //Send device control commands
    fun commands(deviceId:String)="${host}/v1.1/devices/${deviceId}/commands"

    const val scenes="${host}/v1.1/scenes"

    fun execScene(sceneId:String)="${host}/v1.1/scenes/${sceneId}/execute"
}