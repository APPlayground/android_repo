package com.example.layoutcontentsetting

import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter

@Composable
fun ImagePickerTest(
    innerPadding: PaddingValues
) {
    var imageUri by remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri
    }
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocument()
    ) { uri: Uri? ->
        println("선택된 파일: $uri")
    }
    BackHandler(true) {  }
    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(color = Color.White)
            .fillMaxSize()
    ) {
        Button(onClick = { launcher.launch("image/*") }) {
            Text(text = "Load Image")
        }
        Button(onClick = { filePickerLauncher.launch(arrayOf("application/pdf", "text/plain")) }) {
            Text(text = "Load Image")
        }
        Image(
            painter = rememberAsyncImagePainter(imageUri),
            contentDescription = "My Image"
        )
        CouponImageRegisterButton(
            onClick = {}
        )
    }
}

@Composable
fun CouponImageRegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    OutlinedButton(
        onClick = onClick,
        modifier = modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(12.dp),
        border = BorderStroke(1.dp, Color(0xFF00B8FF)),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = Color(0xFF00B8FF)
        )
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // 이미지 리소스 추가 필요
            contentDescription = "쿠폰 이미지",
            modifier = Modifier.size(20.dp),
            tint = Color(0xFF00B8FF)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Text(
            text = "쿠폰 이미지로 등록",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color(0xFF00B8FF)
        )
    }
}

