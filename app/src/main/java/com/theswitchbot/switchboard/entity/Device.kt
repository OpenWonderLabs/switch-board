package com.theswitchbot.switchboard.entity

import com.theswitchbot.switchboard.util.gson


abstract class BaseDevice(
    var deviceId: String,
    var deviceName: String,
    var deviceType: String=Consts.unknown,
)

class Bot(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for curtain and curtain3
class Curtain(
    deviceId: String,
    deviceName: String,
    deviceType: String,
    var curtainDevicesIds:List<String>,
    var calibrate:Boolean,
    var group:Boolean,
    var master:Boolean,
    var openDirection:String
):BaseDevice(deviceId, deviceName, deviceType)

//for Hub/Hub Plus/Hub Mini/Hub 2
class Hub(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Meter/Meter Plus/Outdoor Meter
class Meter(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Lock and Lock Pro
class Lock(
    deviceId: String,
    deviceName: String,
    deviceType: String,
    var group:Boolean,
    var master:Boolean,
    var groupName:String,
    var lockDevicesIds:List<String>
):BaseDevice(deviceId, deviceName, deviceType)

//for Keypad and Keypad Touch
class Keypad(
    deviceId: String,
    deviceName: String,
    deviceType: String,
    var lockDeviceId:String,
    var keyList:List<KeypadKey>,
):BaseDevice(deviceId, deviceName, deviceType)

//for remote
class Remote(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Contact Sensor/Motion Sensor/Water Leak Detector
class Sensor(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Ceiling Light/Ceiling Light Pro/Strip Light/Color Bulb
class Light(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Plug/Plug mini
class Plug(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for S1/S1 plus/S10
class RobotVacuumCleaner(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

class Humidifier(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

//for Indoor Cam/Pan/Tilt Cam/Pan/Tilt Cam 2K
class Camera(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

class BlindTilt(
    deviceId: String,
    deviceName: String,
    deviceType: String,
    val version:Int,
    val blindTiltDevicesIds:List<String>,
    val calibrate:Boolean,
    val group:Boolean,
    val master:Boolean,
    val direction:String,
    val slidePosition:Int
):BaseDevice(deviceId, deviceName, deviceType)


class BatteryCircularFan(
    deviceId: String,
    deviceName: String,
    deviceType: String
):BaseDevice(deviceId, deviceName, deviceType)

class IRDevice(
    deviceId: String,
    deviceName: String,
    val remoteType: String,
    val hubDeviceId:String
):BaseDevice(deviceId, deviceName, DeviceType.IRRemote)


fun createDevice(irDevice:Boolean,map:Map<String,Any>):BaseDevice{
    return if(irDevice){
        gson.fromJson(gson.toJson(map),IRDevice::class.java).apply {
            deviceType=DeviceType.IRRemote
        }
    }else{
        val type= map["deviceType"] ?:Consts.unknown
        val data=if(map.containsKey("deviceType").not()){
            map.toMutableMap().apply { put("deviceType",Consts.unknown)}
        }else{
            map
        }
        val typeClass:Class<out BaseDevice> = when(type){
            DeviceType.Bot-> Bot::class.java
            DeviceType.Curtain-> Curtain::class.java
            DeviceType.Curtain3-> Curtain::class.java
            DeviceType.Hub-> Hub::class.java
            DeviceType.HubPlus-> Hub::class.java
            DeviceType.HubMini-> Hub::class.java
            DeviceType.Hub2-> Hub::class.java
            DeviceType.Meter-> Meter::class.java
            DeviceType.MeterPlus-> Meter::class.java
            DeviceType.OutdoorMeter-> Meter::class.java
            DeviceType.Lock-> Lock::class.java
            DeviceType.LockPro-> Lock::class.java
            DeviceType.Keypad->Keypad::class.java
            DeviceType.KeypadTouch-> Keypad::class.java
            DeviceType.Remote-> Remote::class.java
            DeviceType.MotionSensor-> Sensor::class.java
            DeviceType.ContactSensor-> Sensor::class.java
            DeviceType.WaterLeakDetector-> Sensor::class.java
            DeviceType.CeilingLight-> Light::class.java
            DeviceType.CeilingLightPro-> Light::class.java
            DeviceType.PlugMiniUS-> Plug::class.java
            DeviceType.PlugMiniJP-> Plug::class.java
            DeviceType.Plug-> Plug::class.java
            DeviceType.StripLight-> Light::class.java
            DeviceType.ColorBulb-> Light::class.java
            DeviceType.S1-> RobotVacuumCleaner::class.java
            DeviceType.S1Plus-> RobotVacuumCleaner::class.java
            DeviceType.S10-> RobotVacuumCleaner::class.java
            DeviceType.K10Plus-> RobotVacuumCleaner::class.java
            DeviceType.Humidifier-> Humidifier::class.java
            DeviceType.IndoorCam-> Camera::class.java
            DeviceType.PanTiltCam-> Camera::class.java
            DeviceType.PanTiltCam2K-> Camera::class.java
            DeviceType.BlindTilt-> BlindTilt::class.java
            DeviceType.BatteryFan-> BatteryCircularFan::class.java
            else->Bot::class.java
        }
        gson.fromJson(gson.toJson(data),typeClass)
    }
}
