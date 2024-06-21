package com.example.demoprojectmultimodule

sealed class Screen(val route:String) {
    object MainScreen : Screen("fixtures_listing")
    object DetailScreen : Screen("fixtures_details")
    object StandingScreen : Screen("standings")

    fun withArgs(vararg args:String?):String{
        return buildString {
            append(route)
            args.forEach { arg->
                append("/$arg")
            }
        }
    }
}