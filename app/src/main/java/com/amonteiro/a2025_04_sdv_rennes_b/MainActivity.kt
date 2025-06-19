package com.amonteiro.a2025_04_sdv_rennes_b

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.amonteiro.a2025_04_sdv_rennes_b.ui.AppNavigation
import com.amonteiro.a2025_04_sdv_rennes_b.ui.theme._2025_04_sdv_rennes_bTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            _2025_04_sdv_rennes_bTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                    AppNavigation(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

    }
}