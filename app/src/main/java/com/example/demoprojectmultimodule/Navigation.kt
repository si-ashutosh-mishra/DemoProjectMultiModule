package com.example.demoprojectmultimodule

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.common_webview.presentation.WebViewScreen
import com.example.feature_app_home.presentation.apphome.AppHome
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo
import com.example.feature_video_listing.presentation.videolist.typetwo.VideoListingTypeTwo
import com.example.photo_listing.presentation.photolist.typetwo.PhotoListingTypeTwo
import com.example.photo_listing.presentation.photolist.typetwo.photodetails.PhotoDetailsTypeTwo
import com.example.standing.presentation.standing.StandingsScreen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.PhotoListingScreen.route) {
        composable(route = Screen.PhotoListingScreen.route) {
           PhotoListingTypeTwo(navController = navController)
            /* FixtureScreenTypeOne {
    NavHost(navController = navController, startDestination = Screen.AppHomeScreen.route) {
        composable(route = Screen.MainScreen.route) {
            FixtureScreenTypeOne {
                navController.navigate(Screen.DetailScreen.withArgs("3841"))
            }*/
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
            AppHome(onFixtureViewMoreClick = {
                navController.navigate(Screen.MainScreen.route)
            }, onFixtureItemClick = {
                //
            }, onStandingViewMoreClick = {
                navController.navigate(Screen.StandingDetailsScreen.route)
            })
        }
        composable(route = Screen.WebViewScreen.route) {
            WebViewScreen(
                "WebView",
                "https://www.punjabkingsipl.in/news/icc-t20-world-cup-2024-arshdeep-rabada-and-bairstow-dazzle-in-the-super-eights",
                false
            ) {

            }
        }
        composable(
            route = Screen.PhotoDetails.route,
            arguments = listOf(
                navArgument("titleAlias"){
                    type = NavType.StringType
                    nullable = true
                }))
        {titleAlias->
            PhotoDetailsTypeTwo(
                navController = navController,
                titleAlias = titleAlias.arguments?.getString("titleAlias"))
        }
    }
}