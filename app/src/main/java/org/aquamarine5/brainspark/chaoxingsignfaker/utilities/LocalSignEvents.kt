/*
 * Copyright (c) 2025-2026, @aquamarine5 (@海蓝色的咕咕鸽). All Rights Reserved.
 * Author: aquamarine5@163.com (Github: https://github.com/aquamarine5) and Brainspark (previously RenegadeCreation)
 * Repository: https://github.com/aquamarine5/ChaoxingSignFaker
 */

package org.aquamarine5.brainspark.chaoxingsignfaker.utilities

import android.content.Context
import org.aquamarine5.brainspark.chaoxingsignfaker.entity.ChaoxingLocationSignEntity
import java.security.MessageDigest

object LocalSignEvents {
    fun md5(str: String): String {
        return MessageDigest.getInstance("MD5").digest(str.toByteArray()).toHexString()
    }

    suspend fun onSignGestureEvent(
        context: Context,
        name: String, isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onGestureSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }

    suspend fun onSignCodeEvent(
        context: Context,
        name: String, isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onPasswordSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }

    suspend fun onSignLocationEvent(
        context: Context,
        postLocationEntity: ChaoxingLocationSignEntity,
        name: String, isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onLocationSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }

    suspend fun onSignQRCodeEvent(
        context: Context,
        name: String,
        isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onQRCodeSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }

    suspend fun onSignClickEvent(
        context: Context,
        name: String,
        isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onClickSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }

    suspend fun onSignPhotoEvent(
        context: Context,
        name: String,
        isOtherUser: Boolean = false
    ) {

        ChaoxingAnalyser.onPhotoSignEvent(context)
        if (isOtherUser) ChaoxingAnalyser.onOtherUserSignEvent(context)
    }
}
