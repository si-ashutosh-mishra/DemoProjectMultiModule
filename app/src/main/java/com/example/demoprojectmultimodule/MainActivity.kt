package com.example.demoprojectmultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.demoprojectmultimodule.ui.theme.DemoProjectMultiModuleTheme
import com.example.feature_fixtures.presentation.fixture.FixtureTypeOne
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            DemoProjectMultiModuleTheme {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "match_screen") {
                    composable(route = "match_screen") {
                        FixtureTypeOne(
                            matchCount = 5,
                            cardBackGroundColor = R.color.teal_200,
                            cardBorderColor = R.color.purple_500
                        )
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