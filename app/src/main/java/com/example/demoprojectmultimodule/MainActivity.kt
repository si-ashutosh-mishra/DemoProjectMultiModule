package com.example.demoprojectmultimodule

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.base.ui.common.BaseViewModel
import com.example.base_navigation.DialogDestination
import com.example.base_navigation.LocalProvider
import com.example.base_navigation.ScreenDestination
import com.example.base_navigation.SheetDestination
import com.example.base_navigation.navigationcomponents.NavController
import com.example.base_navigation.navigationcomponents.rememberNavController
import com.example.demoprojectmultimodule.ui.theme.DemoProjectMultiModuleTheme
import com.example.feature_fixtures.presentation.fixture.typeone.FixtureScreenTypeOne
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
//        val viewModelActivity by viewModels<BaseViewModel>()
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()

        setContent {
            DemoProjectMultiModuleTheme {
//                LocalProvider(navHost(),dialogHost(),sheetHost()/*,viewModelActivity*/)
                AppNavigation()
//                Navigation()
            }
        }
    }
}

@Composable
fun navHost(): NavController<ScreenDestination> {
    return rememberNavController(
        startDestination = ScreenDestination.StandingMainScreen
    )
}
@Composable
fun dialogHost(): NavController<DialogDestination> {
    return rememberNavController(
        initialBackstack = emptyList()
    )
}
@Composable
fun sheetHost(): NavController<SheetDestination> {
    return rememberNavController(
        initialBackstack = emptyList()
    )
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!", modifier = modifier
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    DemoProjectMultiModuleTheme {
        Greeting("Android")
    }
}