package com.example.feature_fixtures.presentation.fixture.typeone

import android.widget.Toast
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.EventState
import com.example.feature_fixtures.presentation.fixture.FixtureViewModel


@Composable
fun FixtureScreenTypeOne(
    isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int? = null,
    @DrawableRes liveLogo: Int = R.drawable.ic_live,
    @DrawableRes recentLogo: Int = R.drawable.ic_recent,
    @DrawableRes upcomingLogo: Int = R.drawable.ic_upcoming,
    matchNumberTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    teamNameTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    matchStatusTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    timeStampTextStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    highLightedScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    highLightedOverStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalScoreStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    generalOverStyle: TextStyle = TextStyle(
        color = Color.Black,
        textAlign = TextAlign.Center
    ),
    @DrawableRes matchCardBackGroundImage: Int? = null,
    @ColorRes cardBackGroundColor: Int? = null,
    @ColorRes cardBorderColor: Int = R.color.black,
    teamId: String? = null,
    onClickItem: (name: String?) -> Unit
) {
    val context = LocalContext.current
    val viewModel: FixtureViewModel = hiltViewModel()

    val fixtureList by viewModel.fixture.observeAsState(initial = emptyList())

    val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

    DisposableEffect(lifecycleOwner) {
        // Create an observer that triggers our remembered callbacks
        // for lifecycle events
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_CREATE -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_CREATE",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Lifecycle.Event.ON_START -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_START",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Lifecycle.Event.ON_RESUME -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_RESUME",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Lifecycle.Event.ON_PAUSE -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_PAUSE",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Lifecycle.Event.ON_STOP -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_STOP",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                Lifecycle.Event.ON_DESTROY -> {
                    Toast.makeText(
                        context,
                        "DisposableEffectWithLifeCycle ON_DESTROY",
                        Toast.LENGTH_SHORT
                    ).show()
                }

                else -> {}
            }
        }

        lifecycleOwner.lifecycle.addObserver(observer)

        // When the effect leaves the Composition, remove the observer
        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)

            Toast.makeText(
                context,
                "DisposableEffectWithLifeCycle composition EXIT",
                Toast.LENGTH_SHORT
            )
                .show()
        }
    }

    LaunchedEffect(
        key1 = Unit
    ) {
        viewModel.getFixtureList(teamId)
    }

    LazyColumn(
        modifier = Modifier.fillMaxWidth()
    ) {
        items(fixtureList) {
            when (it?.eventState) {
                EventState.RESULT -> {
                    RecentMatchCardTypeOne(
                        data = it,
                        isSponsorLogoRequired = isSponsorLogoRequired,
                        recentLogo = recentLogo,
                        sponsorLogo = sponsorLogo,
                        matchNumberTextStyle = matchNumberTextStyle,
                        teamNameTextStyle = teamNameTextStyle,
                        matchStatusTextStyle = matchStatusTextStyle,
                        timeStampTextStyle = timeStampTextStyle,
                        highLightedScoreStyle = highLightedScoreStyle,
                        highLightedOverStyle = highLightedOverStyle,
                        generalScoreStyle = generalScoreStyle,
                        generalOverStyle = generalOverStyle,
                        matchCardBackGroundImage = matchCardBackGroundImage,
                        cardBackGroundColor = cardBackGroundColor,
                        cardBorderColor = cardBorderColor
                    )
                }

                EventState.LIVE -> {
                    LiveMatchCardTypeOne(
                        data = it,
                        isSponsorLogoRequired = isSponsorLogoRequired,
                        liveLogo = liveLogo,
                        sponsorLogo = sponsorLogo,
                        matchNumberTextStyle = matchNumberTextStyle,
                        teamNameTextStyle = teamNameTextStyle,
                        matchStatusTextStyle = matchStatusTextStyle,
                        timeStampTextStyle = timeStampTextStyle,
                        highLightedScoreStyle = highLightedScoreStyle,
                        highLightedOverStyle = highLightedOverStyle,
                        generalScoreStyle = generalScoreStyle,
                        generalOverStyle = generalOverStyle,
                        matchCardBackGroundImage = matchCardBackGroundImage,
                        cardBackGroundColor = cardBackGroundColor,
                        cardBorderColor = cardBorderColor
                    )
                }

                EventState.UPCOMING -> {
                    UpcomingMatchCardTypeOne(
                        data = it,
                        isSponsorLogoRequired = isSponsorLogoRequired,
                        upcomingLogo = upcomingLogo,
                        sponsorLogo = sponsorLogo,
                        matchNumberTextStyle = matchNumberTextStyle,
                        matchStatusTextStyle = matchStatusTextStyle,
                        timeStampTextStyle = timeStampTextStyle,
                        matchCardBackGroundImage = matchCardBackGroundImage,
                        cardBackGroundColor = cardBackGroundColor,
                        cardBorderColor = cardBorderColor
                    ){
                        onClickItem(it)
                    }
                }

                else -> {}
            }
        }
    }


}