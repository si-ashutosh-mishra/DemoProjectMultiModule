package com.example.demoprojectmultimodule

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demoprojectmultimodule.ui.theme.DemoProjectMultiModuleTheme
import com.example.feature_news.presentation.news.typeone.NewsTypeOne
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            DemoProjectMultiModuleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "match_screen") {
                    composable(route = "match_screen") {
                        Scaffold(
                            topBar = {
                                TopAppBar(title = { Text(text = "News") },
                                    navigationIcon = {
                                        IconButton(onClick = {}) {
                                            Icon(Icons.Filled.ArrowBack, "backIcon")
                                        }
                                    }
                                )
                            }){
                            Column {
                                NewsTypeOne()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DemoProjectMultiModuleTheme {
        Greeting("Android")
    }
}