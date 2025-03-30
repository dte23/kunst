package com.example.kunsthandler

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.kunsthandler.ui.KunsthandlerApp
import com.example.kunsthandler.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme(
                dynamicColor = false
            ) {
                KunsthandlerApp()
            }
        }
    }
}