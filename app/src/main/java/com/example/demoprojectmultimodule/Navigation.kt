package com.example.demoprojectmultimodule

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.feature_fixtures.presentation.fixture.typeone.FixtureScreenTypeOne
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo
import com.example.feature_squad.presentation.squadfragment.typetwo.SquadFragmentVerticalScroll
import com.example.standing.presentation.standing.StandingsScreen
import com.example.standing.presentation.standinghome.StandingHome

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.SquadScreen.route){
        composable(route = Screen.MainScreen.route){
            FixtureScreenTypeOne() {
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
        composable(route = Screen.StandingMainScreen.route) {
            StandingHome(requiredTeamCount = 5, onViewMoreClick = {
                navController.navigate(Screen.StandingDetailsScreen.route)
            }, showTitle = true, showMore = true)

        }
        composable(route = Screen.StandingDetailsScreen.route) {
            StandingsScreen(navController = navController)
        }
        composable(route = Screen.SquadScreen.route){
            SquadFragmentVerticalScroll()
        }
    }
}