/*
 * Copyright (c) 2025-2026, @aquamarine5 (@海蓝色的咕咕鸽). All Rights Reserved.
 * Author: aquamarine5@163.com (Github: https://github.com/aquamarine5) and Brainspark (previously RenegadeCreation)
 * Repository: https://github.com/aquamarine5/ChaoxingSignFaker
 */

package org.aquamarine5.brainspark.chaoxingsignfaker.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.aquamarine5.brainspark.chaoxingsignfaker.utilities.ChaoxingAnalyser

@Composable
fun AnalyserCard() {
    val context = LocalContext.current
    val data = ChaoxingAnalyser.createStateAnalyser()
    LaunchedEffect(context) { ChaoxingAnalyser.setupStateAnalyser(context) }
    Card {
        Column(Modifier.padding(16.dp)) {
            Text("本机签到统计")
            if (!data.isLoaded.value) Text("正在读取…")
            else {
                Text("普通：${data.clickSignCount.value} · 手势：${data.gestureSignCount.value}")
                Text("签到码：${data.passwordSignCount.value} · 二维码：${data.qrcodeSignCount.value}")
                Text("位置：${data.locationSignCount.value} · 拍照：${data.photoSignCount.value}")
                Text("仅保存在此设备，不上传排行榜。")
            }
        }
    }
}
