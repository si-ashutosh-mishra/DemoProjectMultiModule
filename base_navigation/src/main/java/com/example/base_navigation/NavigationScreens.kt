package com.example.base_navigation

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class SheetDestination : Parcelable {
    @Parcelize
    data class First(val text: String) : SheetDestination()

}


sealed class ScreenDestination : Parcelable {

    @Parcelize
    data object StandingMainScreen : ScreenDestination()

    @Parcelize
    data object MainScreen : ScreenDestination()

    @Parcelize
    data class DetailScreen(val text: String) : ScreenDestination()

    @Parcelize
    data object StandingDetailsScreen : ScreenDestination()

}

sealed class DialogDestination : Parcelable {

    @Parcelize
    data object First : DialogDestination()

    @Parcelize
    data object Second : DialogDestination()

    @Parcelize
    data object Third : DialogDestination()

}