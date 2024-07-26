package com.theswitchbot.switchboard.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.theswitchbot.switchboard.R
import com.theswitchbot.switchboard.entity.DeviceType


object CardHelper {
    fun cardBg(deviceType:String):Color{
        return when(deviceType){
            in DeviceType.hubs -> DeviceType.cHubs
            in DeviceType.irRemotes -> DeviceType.cIrRemotes
            in DeviceType.locks -> DeviceType.cLocks
            in DeviceType.cams -> DeviceType.cCams
            in DeviceType.lights -> DeviceType.cLights
            in DeviceType.environments -> DeviceType.cEnvironments
            in DeviceType.robots -> DeviceType.cRobots
            in DeviceType.curtains -> DeviceType.cCurtains
            in DeviceType.sensors -> DeviceType.cSensors
            in DeviceType.switches -> DeviceType.cSwitches
            in DeviceType.remotes -> DeviceType.cRemotes
            else-> DeviceType.cUnknown
        }
    }

    fun cardWidth(deviceType: String):Dp{
        return when(deviceType){
            in DeviceType.hubs ->150.dp
            in DeviceType.irRemotes ->200.dp
            in DeviceType.locks ->200.dp
            in DeviceType.cams ->150.dp
            in DeviceType.lights ->250.dp
            in DeviceType.environments ->250.dp
            in DeviceType.robots ->200.dp
            in DeviceType.curtains ->220.dp
            in DeviceType.sensors ->160.dp
            in DeviceType.switches ->200.dp
            in DeviceType.remotes ->150.dp
            else->150.dp
        }
    }

    fun deviceIcon(deviceType:String):Int{
        if(deviceType== DeviceType.IRRemote){
            return R.mipmap.icon_32pt_urc
        }
        return when(deviceType){
            DeviceType.Bot ->R.mipmap.icon_32pt_bot
            DeviceType.Curtain ->R.mipmap.icon_32pt_curtain3
            DeviceType.Curtain3 ->R.mipmap.icon_32pt_curtain3
            DeviceType.Hub ->R.mipmap.icon_32pt_hub_plus
            DeviceType.HubPlus ->R.mipmap.icon_32pt_hub_plus
            DeviceType.HubMini ->R.mipmap.icon_32pt_hub_mini
            DeviceType.Hub2 ->R.mipmap.icon_32pt_hub2
            DeviceType.Meter ->R.mipmap.icon_32pt_meterplus
            DeviceType.MeterPlus ->R.mipmap.icon_32pt_meterplus
            DeviceType.OutdoorMeter ->R.mipmap.icon_32pt_outdoor_meter
            DeviceType.Lock ->R.mipmap.icon_32pt_lock
            DeviceType.LockPro ->R.mipmap.icon_32pt_lockpro
            DeviceType.Keypad ->R.mipmap.icon_32pt_keypad
            DeviceType.KeypadTouch ->R.mipmap.icon_32pt_keypad_touch
            DeviceType.Remote ->R.mipmap.icon_32pt_remote
            DeviceType.MotionSensor ->R.mipmap.icon_32pt_motion_sensor
            DeviceType.ContactSensor ->R.mipmap.icon_32pt_contact_sensor
            DeviceType.WaterLeakDetector ->R.mipmap.icon_32pt_water_leak_detector
            DeviceType.CeilingLight ->R.mipmap.icon_32pt_ceiling_light
            DeviceType.CeilingLightPro ->R.mipmap.icon_32pt_ceiling_light
            DeviceType.PlugMiniUS ->R.mipmap.icon_32pt_plug_mini_us
            DeviceType.PlugMiniJP ->R.mipmap.icon_32pt_plug_mini_jp
            DeviceType.Plug ->R.mipmap.icon_32pt_plug
            DeviceType.StripLight ->R.mipmap.icon_32pt_led_strip
            DeviceType.ColorBulb ->R.mipmap.icon_32pt_bulb
            DeviceType.S1 ->R.mipmap.icon_32pt_s1
            DeviceType.S1Plus ->R.mipmap.icon_32pt_s1_plus
            DeviceType.Humidifier ->R.mipmap.icon_32pt_humidifier
            DeviceType.S10 ->R.mipmap.icon_32pt_s10
            DeviceType.K10Plus ->R.mipmap.icon_32pt_s10
            DeviceType.IndoorCam ->R.mipmap.icon_32pt_indoor_cam
            DeviceType.PanTiltCam ->R.mipmap.icon_32pt_pan_tilt_cam
            DeviceType.PanTiltCam2K ->R.mipmap.icon_32pt_pan_tilt_cam
            DeviceType.BlindTilt ->R.mipmap.icon_32pt_blind_tilt
            DeviceType.BatteryFan ->R.mipmap.icon_32pt_bc_fan
            else -> R.mipmap.icon_32pt_unknown
        }
    }

}