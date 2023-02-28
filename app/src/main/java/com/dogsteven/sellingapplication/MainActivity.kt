package com.dogsteven.sellingapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.dogsteven.sellingapplication.navigation.NavigationComposable
import com.dogsteven.sellingapplication.ui.theme.SellingApplicationTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SellingApplicationTheme {
                NavigationComposable()
            }
        }
    }
}