package com.example.demoprojectmultimodule

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_fixtures.presentation.fixture.typeone.FixtureScreenTypeOne
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route){
        composable(route = Screen.MainScreen.route){
            FixtureScreenTypeOne() {
                navController.navigate(Screen.DetailScreen.withArgs("3841"))
            }
        }
        composable(route = Screen.DetailScreen.route + "/{name}", arguments = listOf(
            navArgument("name"){
                type = NavType.StringType
                defaultValue = "Phillip"
                nullable = true
            }
        )){ entry->
            FixtureScreenTypeTwo(
                navController = navController,
                teamId = entry.arguments?.getString("name")
            ) {

            }
        }
    }
}