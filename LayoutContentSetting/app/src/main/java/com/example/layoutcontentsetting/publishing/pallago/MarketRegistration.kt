package com.example.layoutcontentsetting.publishing.pallago

import android.R
import android.R.attr.onClick
import android.graphics.fonts.FontStyle
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketRegistration(
    innerPadding: PaddingValues
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .background(color = Color.White)
            .padding(horizontal = 24.dp)
            .fillMaxSize()
    ) {
        Row {
            Text(
                text = "장터 등록",
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                fontSize = 24.sp
            )
        }
        Text(
            text = """
                기프티콘 원본 이미지를 올리면 자동 인식하여 등록됩니다.
                화면을 캡쳐하거나 편집한 이미지가 아닌, 원본 이미지를 올려주세요.
            """.trimIndent(),
            color = Color.Black,
            fontSize = TextUnit.Unspecified
        )
        Box(
            modifier = Modifier
                .shadow(
                    elevation = 8.dp,
                    shape = RoundedCornerShape(12.dp),
                    clip = true
                )
                .background(Color.White, shape = RoundedCornerShape(12.dp))
        ) {
            OutlinedButton(
                onClick = { showBottomSheet = true },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                border = BorderStroke(1.dp, Color(0xFF00B8FF)),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = Color(0xFF00B8FF)
                )
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_menu_add), // 이미지 리소스 추가 필요
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
            if (showBottomSheet) {
                ModalBottomSheet(
                    onDismissRequest = {
                        showBottomSheet = false
                    },
                    sheetState = sheetState
                ) {
                    // Sheet content
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }
                }
            }
        }
    }
}