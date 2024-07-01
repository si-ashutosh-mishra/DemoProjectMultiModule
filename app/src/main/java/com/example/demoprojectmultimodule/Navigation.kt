package com.example.demoprojectmultimodule

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_app_home.presentation.apphome.AppHome
import com.example.feature_fixtures.presentation.fixture.typeone.FixtureScreenTypeOne
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo
import com.example.standing.presentation.standing.StandingsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AppHomeScreen.route) {
        composable(route = Screen.MainScreen.route) {
            FixtureScreenTypeOne {
                navController.navigate(Screen.DetailScreen.withArgs("3841"))
            }
        }
        composable(
            route = Screen.DetailScreen.route + "/{name}", arguments = listOf(navArgument("name") {
                type = NavType.StringType
                defaultValue = "Phillip"
                nullable = true
            })
        ) { entry ->
            FixtureScreenTypeTwo(
                navController = navController, teamId = entry.arguments?.getString("name")
            ) {

            }
        }

        composable(route = Screen.StandingDetailsScreen.route) {
            StandingsScreen(navController = navController)
        }

        composable(route = Screen.AppHomeScreen.route) {
            AppHome {
                navController.navigate(Screen.StandingDetailsScreen.route)
            }
        }
    }
}