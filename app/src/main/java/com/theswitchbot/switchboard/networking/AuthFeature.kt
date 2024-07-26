package com.theswitchbot.switchboard.networking

import com.theswitchbot.switchboard.APP
import com.theswitchbot.switchboard.util.SPUtil
import io.ktor.client.plugins.api.createClientPlugin
import io.ktor.client.request.header
import java.time.Instant
import java.util.Base64
import java.util.UUID
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec


val AuthPlugin=createClientPlugin("AuthPlugin"){
    onRequest { request, _ ->
        val token = SPUtil.getToken(APP.context)
        val secret = SPUtil.getSecret(APP.context)

        if(token.isNotEmpty()&&secret.isNotEmpty()) {
            val nonce = UUID.randomUUID().toString()
            val time = "" + Instant.now().toEpochMilli()
            val data = token + time + nonce
            val secretKeySpec = SecretKeySpec(secret.toByteArray(charset("UTF-8")), "HmacSHA256")
            val mac = Mac.getInstance("HmacSHA256")
            mac.init(secretKeySpec)
            val signature =
                String(Base64.getEncoder().encode(mac.doFinal(data.toByteArray(charset("UTF-8")))))

            request.headers.append("Authorization", token)
            request.headers.append("sign", signature)
            request.headers.append("nonce", nonce)
            request.headers.append("t", time)
        }

    }
}