package com.example.agroverdaspados

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.agroverdaspados.ui.theme.AgroVerdaSPAdosTheme
import com.example.agroverdaspados.ui.theme.navigation.AppNavigation

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AgroVerdaSPAdosTheme {
                AppNavigation()
            }
        }
    }
}