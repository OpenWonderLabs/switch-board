package com.theswitchbot.switchboard.entity

import android.hardware.camera2.CameraCaptureSession
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.theswitchbot.switchboard.util.gson


abstract class Status(
    val deviceId: String,
    val deviceType: String,
    val hubDeviceId: String
)

class EmptyStatus(
    deviceId: String
) : Status(deviceId, Consts.unknown, "")


class FailedStatus(deviceId: String): Status(deviceId, Consts.unknown, "")

class BotStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var power: String,
    var battery: Int,
    var version: String,
    var deviceMode: String,
) : Status(deviceId, deviceType, hubDeviceId)

class CurtainStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var calibrate: String,
    var group: Boolean,
    var battery: Int,
    var version: String,
    var slidePosition: Int
) : Status(deviceId, deviceType, hubDeviceId)

class MeterStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var temperature: Float,
    var humidity: Int,
    var version: String,
    var battery: Int
) : Status(deviceId, deviceType, hubDeviceId)

class LockStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var battery: Int,
    var version: String,
    var lockState: String,
    var doorState: String,
    var calibrate: Boolean
) : Status(deviceId, deviceType, hubDeviceId)

class MotionSensorStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var battery: Int,
    var version: String,
    var moveDetected: Boolean,
    var brightness: String
) : Status(deviceId, deviceType, hubDeviceId)

class ContactSensorStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var battery: Int,
    var version: String,
    var moveDetected: Boolean,
    var brightness: String,
    var openState: String
) : Status(deviceId, deviceType, hubDeviceId)

class WaterLeakDetectorStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var battery: Int,
    var version: String,
    var status: Int,
) : Status(deviceId, deviceType, hubDeviceId)

class LightStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var power: String,
    var version: String,
    var brightness: Int,
    var colorTemperature: Int
) : Status(deviceId, deviceType, hubDeviceId)


class PlugMiniStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var power: String,
    var voltage: Float,
    var version: String,
    var weight: Float,
    var electricityOfDay: Int,
    var electricCurrent: Float
) : Status(deviceId, deviceType, hubDeviceId)


class PlugStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var power: String,
    var version: String,
) : Status(deviceId, deviceType, hubDeviceId)



class RobotVacuumCleanerStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var workingStatus: String,
    var onlineStatus: String,
    var battery: Int,
) : Status(deviceId, deviceType, hubDeviceId)

class HumidifierStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var power: String,
    var humidity: Int,
    var temperature: Float,
    var nebulizationEfficiency: Int,
    var auto: Boolean,
    var childLock: Boolean,
    var sound: Boolean,
    var lackWater: Boolean
) : Status(deviceId, deviceType, hubDeviceId)

class BlindTiltStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var version: String,
    var calibrate: Boolean,
    var group: Boolean,
    var moving: Boolean,
    var direction: String,
    var slidePosition: Int
) : Status(deviceId, deviceType, hubDeviceId)

class Hub2Status(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var temperature: Float,
    var lightLevel: Int,
    var version: String,
    var humidity: Int,
) : Status(deviceId, deviceType, hubDeviceId)

class BatteryCircularFanStatus(
    deviceId: String,
    deviceType: String,
    hubDeviceId: String,
    var mode: String,
    var battery: Int,
    var version: String,
    var power: String,
    var nightStatus: Int,
    var oscillation: String,
    var verticalOscillation: String,
    var chargingStatus: String,
    var fanSpeed: Int
) : Status(deviceId, deviceType, hubDeviceId)


fun createStatus(deviceType: String, map: Map<String, Any>): Status {
    val typeClass: Class<out Status> = when (deviceType) {
        DeviceType.Bot -> BotStatus::class.java
        DeviceType.Curtain -> CurtainStatus::class.java
        DeviceType.Curtain3 -> CurtainStatus::class.java
        DeviceType.Hub -> EmptyStatus::class.java
        DeviceType.HubPlus -> EmptyStatus::class.java
        DeviceType.HubMini -> EmptyStatus::class.java
        DeviceType.Hub2 -> Hub2Status::class.java
        DeviceType.Meter -> MeterStatus::class.java
        DeviceType.MeterPlus -> MeterStatus::class.java
        DeviceType.OutdoorMeter -> MeterStatus::class.java
        DeviceType.Lock -> LockStatus::class.java
        DeviceType.LockPro -> LockStatus::class.java
        DeviceType.Keypad -> LockStatus::class.java
        DeviceType.KeypadTouch -> LockStatus::class.java
        DeviceType.Remote -> EmptyStatus::class.java
        DeviceType.MotionSensor -> MotionSensorStatus::class.java
        DeviceType.ContactSensor -> ContactSensorStatus::class.java
        DeviceType.WaterLeakDetector -> WaterLeakDetectorStatus::class.java
        DeviceType.CeilingLight -> LightStatus::class.java
        DeviceType.CeilingLightPro -> LightStatus::class.java
        DeviceType.PlugMiniUS -> PlugMiniStatus::class.java
        DeviceType.PlugMiniJP -> PlugMiniStatus::class.java
        DeviceType.Plug -> PlugStatus::class.java
        DeviceType.StripLight -> LightStatus::class.java
        DeviceType.ColorBulb -> LightStatus::class.java
        DeviceType.S1 -> RobotVacuumCleanerStatus::class.java
        DeviceType.S1Plus -> RobotVacuumCleanerStatus::class.java
        DeviceType.S10 -> RobotVacuumCleanerStatus::class.java
        DeviceType.Humidifier -> HumidifierStatus::class.java
        DeviceType.IndoorCam -> EmptyStatus::class.java
        DeviceType.PanTiltCam -> EmptyStatus::class.java
        DeviceType.PanTiltCam2K -> EmptyStatus::class.java
        DeviceType.BlindTilt -> BlindTiltStatus::class.java
        DeviceType.BatteryFan -> BatteryCircularFanStatus::class.java
        else -> EmptyStatus::class.java
    }
    return gson.fromJson(gson.toJson(map), typeClass)
}
