package com.example.layoutcontentsetting

import android.app.AlertDialog
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlertDialogScreen(innerPadding: PaddingValues) {
    var showDialog by remember { mutableStateOf(false) }
    var dialogContent by remember { mutableStateOf("") }
    val builder: AlertDialog.Builder = AlertDialog.Builder(LocalContext.current)
    builder
        .setTitle(dialogContent)
        .setPositiveButton("Start") { dialog, id ->
            Log.d("TEST", "게임을 시작하였습니다! $dialog,$id")
        }
        .setNegativeButton("Cancel") { dialog, id ->
            Log.d("TEST", "게임을 취소하였습니다 $dialog,$id")
        }

    val dialog = builder.create()

    Column(
        modifier = Modifier.padding(innerPadding)
    ) {
        TextField(
            value = dialogContent,
            onValueChange = { dialogContent = it }
        )
        Spacer(modifier = Modifier.weight(1f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            showDialog = true
                        }
                    )
                }
                .background(color = Color.Blue, shape = CircleShape),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp),
                text = "눌러봐",
                fontSize = 20.sp,
            )
        }
        if (showDialog) {
            dialog.show()
            showDialog = false
        }
    }
}