package com.theswitchbot.switchboard.entity

import androidx.compose.ui.graphics.Color


object Consts {
    val unknown="unknown"
    val menuBg= Color(0xFF111111)
    val mainBg= Color(0xFF0E0E0E)
    const val on="ON"
    const val off="OFF"

    const val unlock="unlock"
    const val lock="lock"
    const val jammed="jammed"

    const val command="command"
    const val default="default"
    const val commandType="commandType"
    const val parameter="parameter"

    const val pressMode="pressMode"
    const val switchMode="switchMode"
    const val customizeMode="customizeMode"

    const val others="Others"
}

object DeviceType{
    const val Bot="Bot"
    const val Curtain="Curtain"
    const val Curtain3="Curtain3"
    const val Hub="Hub"
    const val HubPlus="Hub Plus"
    const val HubMini="Hub Mini"
    const val Hub2="Hub2"
    const val Meter="Meter"
    const val MeterPlus="MeterPlus"
    const val OutdoorMeter="Outdoor Meter"
    const val Lock="Smart Lock"
    const val LockPro="LockPro"
    const val Keypad="Keypad"
    const val KeypadTouch="Keypad Touch"
    const val Remote="Remote"
    const val MotionSensor="Motion Sensor"
    const val ContactSensor="Contact Sensor"
    const val WaterLeakDetector="Water Detector"
    const val CeilingLight="Ceiling Light"
    const val CeilingLightPro="Ceiling Light Pro"
    const val PlugMiniUS="Plug Mini (US)"
    const val PlugMiniJP="Plug Mini (JP)"
    const val Plug="Plug"
    const val StripLight="Strip Light"
    const val ColorBulb="Color Bulb"
    const val S1="Robot Vacuum Cleaner S1"
    const val S1Plus="Robot Vacuum Cleaner S1 Plus"
    const val Humidifier="Humidifier"
    const val S10="Floor Cleaning Robot S10"
    const val IndoorCam="Indoor Cam"
    const val PanTiltCam="Pan/Tilt Cam"
    const val PanTiltCam2K="Pan/Tilt Cam 2K"
    const val BlindTilt="Blind Tilt"
    const val BatteryFan="BatteryCirculatorFan"
    const val K10Plus="K10+"
    const val IRRemote="IRRemote"

    val hubs= arrayOf(Hub, HubMini, HubPlus, Hub2)
    val irRemotes= arrayOf(IRRemote)
    val locks= arrayOf(Lock, LockPro, Keypad, KeypadTouch)
    val cams= arrayOf(IndoorCam,PanTiltCam,PanTiltCam2K)
    val lights= arrayOf(CeilingLight, CeilingLightPro, ColorBulb, StripLight)
    val environments= arrayOf(Humidifier,BatteryFan)
    val robots= arrayOf(S1, S1Plus, S10)
    val curtains= arrayOf(Curtain, Curtain3, BlindTilt)
    val sensors= arrayOf(Meter, MeterPlus, OutdoorMeter, MotionSensor, ContactSensor,
        WaterLeakDetector)
    val switches= arrayOf(Bot, Plug, PlugMiniJP, PlugMiniUS)
    val remotes=arrayOf(Remote)

    val cHubs= Color(0xFFFF6F61)
    val cIrRemotes= Color(0xFFFFD700)
    val cLocks= Color(0xFF4682B4)
    val cCams= Color(0xFF8A2BE2)
    val cLights= Color(0xFFFF8C00)
    val cEnvironments= Color(0xFF3CB371)
    val cRobots= Color(0xFF1E90FF)
    val cCurtains= Color(0xFFD2691E)
    val cSensors= Color(0xFFFFA07A)
    val cSwitches= Color(0xFF20B2AA)
    val cRemotes=Color(0xFF9370DB)
    val cUnknown=Color(0xFF778899)
    val cOffline=Color(0xFFA9A9A9)

    val colors= arrayOf(
        cHubs, cIrRemotes, cLocks, cCams, cLights,
        cEnvironments, cRobots, cCurtains, cSensors,
        cSwitches, cRemotes, cUnknown, cOffline
    )
}


