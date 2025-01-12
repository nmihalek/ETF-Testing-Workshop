package com.etf.testing.workshop.presentation.common

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.CompositionLocalProvider
import com.etf.testing.workshop.presentation.common.compose.AppNavGraph
import org.koin.androidx.compose.KoinAndroidContext

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            MaterialTheme {
                KoinAndroidContext {
                    AppNavGraph()
                }
            }
        }
    }
}