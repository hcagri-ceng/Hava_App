package com.kampplus.hava

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.kampplus.hava.core.navigation.HavaNavHost
import com.kampplus.hava.core.ui.theme.HavaTheme

/**
 * CP2: Navigasyon destekli ana giriş.
 * rememberNavController() ile NavHost entegrasyonu sağlanmıştır.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HavaTheme {
                val navController = rememberNavController()
                HavaNavHost(navController = navController)
            }
        }
    }
}
