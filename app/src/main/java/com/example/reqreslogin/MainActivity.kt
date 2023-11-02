package com.example.reqreslogin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.rememberNavController
import com.example.reqreslogin.ui.navigation.NavGraph
import com.example.reqreslogin.ui.theme.ReqresLoginTheme
import com.example.reqreslogin.utils.DEFAULT_TOKEN
import com.example.reqreslogin.utils.PreferencesManager
import com.example.reqreslogin.utils.TOKEN_KEY
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ReqresLoginTheme {
                val context = LocalContext.current
                val preferencesManager = remember { PreferencesManager(context = context) }
                val navController = rememberNavController()
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color.White
                ) {
                    val token = preferencesManager.getData(TOKEN_KEY)
                    val isLoggedIn = token != DEFAULT_TOKEN
                    NavGraph(navController = navController, isLogin = isLoggedIn)
                }
            }
        }
    }
}