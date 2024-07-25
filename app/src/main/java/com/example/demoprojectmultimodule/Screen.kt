package com.example.demoprojectmultimodule

sealed class Screen(val route:String) {
    object MainScreen : Screen("fixtures_listing")
    object DetailScreen : Screen("fixtures_details")
    object StandingDetailsScreen : Screen("standings_details")
    object PhotoListingScreen : Screen("photo_listing_type_two")
    object AppHomeScreen : Screen("app_home")
    object PhotoDetails : Screen("PhotoDetails/{titleAlias}")
    object SquadScreen : Screen("squad_listing")
    object PhotoListingGridView : Screen("PhotoListingGridLayout")
    object WebViewScreen : Screen("webview")
    object VideoDetails : Screen("VideoDetails/{titleAlias}")
    fun withArgs(vararg args:String?):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}