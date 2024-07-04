package com.example.base_navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import com.example.base_navigation.navigationcomponents.NavController
import com.example.base_navigation.navigationcomponents.rememberNavController


val LocalNavController = compositionLocalOf<NavController<ScreenDestination>> { error("No NavController found!") }
val LocalDialogController = compositionLocalOf<NavController<DialogDestination>> { error("No DialogController found!") }
val LocalSheetController = compositionLocalOf<NavController<SheetDestination>> { error("No DialogController found!") }
//val LocalLoaderViewModel = compositionLocalOf<BaseViewModel> { error("No LoaderModel found!") }

@Composable
fun LocalProvider(
    navHost:NavController<ScreenDestination> = rememberNavController(startDestination = ScreenDestination.StandingDetailsScreen),
    dialogHost:NavController<DialogDestination> = rememberNavController(initialBackstack = emptyList()),
    sheetHost:NavController<SheetDestination> = rememberNavController(initialBackstack = emptyList()),
//    viewModel: BaseViewModel
    ) {
    CompositionLocalProvider(
        LocalNavController provides navHost,
        LocalDialogController provides dialogHost,
        LocalSheetController provides sheetHost,
//        LocalLoaderViewModel provides viewModel
    ) {}
}