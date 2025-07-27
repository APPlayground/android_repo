package com.example.layoutcontentsetting

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.example.layoutcontentsetting.publishing.pallago.MarketRegistration
import com.example.layoutcontentsetting.ui.theme.LayoutContentSettingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LayoutContentSettingTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MarketRegistration(innerPadding)
                }
            }
        }
    }
}