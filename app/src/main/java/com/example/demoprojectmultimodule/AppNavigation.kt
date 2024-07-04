package com.example.demoprojectmultimodule

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Dialog
import androidx.navigation.compose.rememberNavController
import com.example.base_navigation.DialogDestination
import com.example.base_navigation.LocalDialogController
import com.example.base_navigation.LocalNavController
import com.example.base_navigation.LocalSheetController
import com.example.base_navigation.ScreenDestination
import com.example.base_navigation.SheetDestination
import com.example.base_navigation.navigationcomponents.AnimatedNavHost
import com.example.base_navigation.navigationcomponents.BottomSheetNavHost
import com.example.base_navigation.navigationcomponents.DialogNavHost
import com.example.base_navigation.navigationcomponents.NavAction
import com.example.base_navigation.navigationcomponents.NavBackHandler
import com.example.base_navigation.navigationcomponents.navigate
import com.example.base_navigation.navigationcomponents.pop
import com.example.base_navigation.navigationcomponents.rememberNavController
import com.example.feature_fixtures.presentation.fixture.typeone.FixtureScreenTypeOne
import com.example.feature_fixtures.presentation.fixture.typetwo.FixtureScreenTypeTwo
import com.example.standing.presentation.standing.StandingsScreen
import com.example.standing.presentation.standinghome.StandingHome

@Composable
fun AppNavigation(){
//    val navController = LocalNavController.current
//    val dialogController = LocalDialogController.current
//    val sheetController = LocalSheetController.current

    val navController = rememberNavController<ScreenDestination>(
        startDestination = ScreenDestination.MainScreen
    )
    val dialogController = rememberNavController<DialogDestination>(
        initialBackstack = emptyList()
    )
    val sheetController = rememberNavController<SheetDestination>(
        initialBackstack = emptyList()
    )

    NavBackHandler(navController)
    NavBackHandler(
        controller = sheetController,
        allowEmptyBackstack = true
    )

    AnimatedNavHost(
        controller = navController,
        transitionSpec = { action, _, _ ->
            val direction = if (action == NavAction.Pop) {
                AnimatedContentTransitionScope.SlideDirection.End
            } else {
                AnimatedContentTransitionScope.SlideDirection.Start
            }
            slideIntoContainer(direction) togetherWith slideOutOfContainer(direction)
        },
    ) { destination->
        when (destination) {
//            is ScreenDestination.StandingMainScreen -> StandingsScreen(onViewMoreClick = {
//                navController.navigate(ScreenDestination.StandingDetailsScreen)
//            }, showTitle = true, showMore = true)
//            is ScreenDestination.DetailScreen -> FixtureScreenTypeTwo(
//                null,
//                 teamId = navController.backstack.entries.toString()
//            ) {
//                navController.navigate(ScreenDestination.DetailScreen("3841"))
//            }
            is ScreenDestination.MainScreen ->  FixtureScreenTypeOne {
                navController.navigate(ScreenDestination.DetailScreen("3841"))
            }
            is ScreenDestination.StandingDetailsScreen -> StandingsScreen()
            else -> {}
        }
    }

//    DialogNavHost(dialogController) { destination ->
//        Dialog(onDismissRequest = { dialogController.pop() }) {
//            when (destination) {
//                DialogDestination.First -> {  }
//                DialogDestination.Second -> {  }
//                DialogDestination.Third -> {  }
//            }
//        }
//    }

//    BottomSheetNavHost(
//        controller = sheetController,
//        onDismissRequest = { sheetController.pop() }
//    ) { destination ->
//        Surface {
//            when (destination) {
//               is SheetDestination.First -> { /* ... */ }
//            }
//        }
//    }

    /*NEED TO CHECK W+ETHERE WE ARE GETTING DAta from last index in bottomsheet*/
}


