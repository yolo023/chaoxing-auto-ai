package org.aquamarine5.brainspark.chaoxingsignfaker.components

import android.content.ActivityNotFoundException
import android.content.Intent
import android.widget.Toast
import androidx.core.net.toUri
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import org.aquamarine5.brainspark.chaoxingsignfaker.BuildConfig

@Composable
fun AppUpdateCard() {
    val context = LocalContext.current
    Card {
        Column(Modifier.padding(16.dp)) {
            Text("学习助手 ${BuildConfig.VERSION_NAME}")
            Text("更新由你手动选择，只从本项目 GitHub Releases 获取。")
            TextButton(onClick = {
                runCatching {
                    context.startActivity(Intent(Intent.ACTION_VIEW,
                        "https://github.com/yolo023/chaoxing-auto-ai/releases".toUri()))
                }.onFailure {
                    if (it is ActivityNotFoundException) {
                        Toast.makeText(context, "请安装浏览器后打开发布页", Toast.LENGTH_LONG).show()
                    } else throw it
                }
            }) { Text("打开发布页") }
        }
    }
}
