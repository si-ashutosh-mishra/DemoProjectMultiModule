package com.example.feature_fixtures.presentation.fixture.typetwo

import android.os.CountDownTimer
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.base.utils.CalendarUtils
import com.example.feature_fixtures.R
import com.example.feature_fixtures.business.domain.model.masthead.IPLMatch
import java.text.SimpleDateFormat
import java.util.Date
import java.util.concurrent.TimeUnit

@Composable
fun UpcomingMatchCardTypeTwo(
    data: IPLMatch?,
    isSponsorLogoRequired: Boolean = false,
    @DrawableRes sponsorLogo: Int? = null,
    @DrawableRes upcomingLogo: Int = R.drawable.ic_upcoming,
    matchNumberTextStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    timerTextStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    matchStatusTextStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    timeStampTextStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    timerFooterStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    poweredByStyle: TextStyle = TextStyle(
        color = Color.Black, textAlign = TextAlign.Center
    ),
    @DrawableRes matchCardBackGroundImage: Int? = null,
    @ColorRes cardBackGroundColor: Int? = null,
    @ColorRes cardBorderColor: Int = R.color.black,
    onItemClick: (name: String?) -> Unit
) {
    val teamA = data?.participants?.firstOrNull()
    val teamB = data?.participants?.lastOrNull()
    var countDownTimer1: CountDownTimer? = null
    val daysState = remember {
        mutableStateOf("00")
    }
    val hoursState = remember {
        mutableStateOf("00")
    }
    val minutesState = remember {
        mutableStateOf("00")
    }
    val secondsState = remember {
        mutableStateOf("00")
    }
    try {
        val sdf = SimpleDateFormat(CalendarUtils.SCORE_CARD_MATCH_DATE_FORMAT)
        val date: Date = sdf.parse(data?.startDate)

        val timeRemaining = (date.time - System.currentTimeMillis()).toLong()
        countDownTimer1?.cancel()
        countDownTimer1 = object : CountDownTimer(
            timeRemaining,
            1000
        ) {
            override fun onTick(millis: Long) {
                var millisUntilFinished = millis

                /*val diffSeconds = millisUntilFinished / 1000 % 60
                val diffMinutes = millisUntilFinished / (60 * 1000) % 60
                val diffHours = millisUntilFinished / (60 * 60 * 1000) % 24
                val days = millisUntilFinished / (1000 * 60 * 60 * 24) % 7*/


                /*val diffSeconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)
                val diffMinutes = TimeUnit.SECONDS.toMinutes(diffSeconds)
                val diffHours = TimeUnit.MINUTES.toHours(diffMinutes)
                val days =TimeUnit.HOURS.toDays(diffHours)*/


                val days = TimeUnit.MILLISECONDS.toDays(millisUntilFinished)
                millisUntilFinished -= TimeUnit.DAYS.toMillis(days)

                val hours = TimeUnit.MILLISECONDS.toHours(millisUntilFinished)
                millisUntilFinished -= TimeUnit.HOURS.toMillis(hours)

                val minutes = TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished)
                millisUntilFinished -= TimeUnit.MINUTES.toMillis(minutes)

                val seconds = TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished)

                if (hours > 0 || minutes > 0 || seconds > 0) {
                    daysState.value = String.format("%02d", days)
                    hoursState.value = String.format("%02d", hours)
                    minutesState.value = String.format("%02d", minutes)
                    secondsState.value = String.format("%02d", seconds)

                } else {

                }
            }

            override fun onFinish() {
                //binding?.txtTimmer?.text = context.getString(R.string.finished)
                countDownTimer1?.cancel()
            }
        }
        countDownTimer1?.start()
    } catch (e: Exception) {
        daysState.value = "00"
        hoursState.value = "00"
        minutesState.value = "00"
        secondsState.value = "00"
    }
    Card(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxWidth()
            .padding(all = 8.dp)
            .clickable { onItemClick(data?.eventName) },
        colors = CardDefaults.cardColors(
            containerColor = Color.Transparent,
        ),
        shape = RoundedCornerShape(5.dp),
        border = BorderStroke(0.1.dp, colorResource(cardBorderColor))
    ) {
        Box(
            modifier = Modifier
                .background(if (cardBackGroundColor != null) colorResource(id = cardBackGroundColor) else Color.Transparent)
                .fillMaxWidth()
        ) {
            if (matchCardBackGroundImage != null) {
                Image(
                    painterResource(id = matchCardBackGroundImage),
                    contentDescription = "",
                    contentScale = ContentScale.FillBounds, // or some other scale
                    modifier = Modifier.matchParentSize()
                )
            }
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .background(Color.Transparent)
                    .fillMaxWidth()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "${data?.eventName}, " ?: "", style = matchNumberTextStyle
                    )
                    Text(
                        text = CalendarUtils.getConvertedDate(data?.startDate ?: ""),
                        style = timeStampTextStyle
                    )

                    Spacer(Modifier.weight(1f))

                    Image(
                        painterResource(upcomingLogo),
                        contentDescription = "",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .height(15.dp)
                            .background(Color.Black)
                    )
                }
                Spacer(
                    modifier = Modifier
                        .padding(vertical = 10.dp)
                        .height(1.dp)
                        .fillMaxWidth()
                        .background(Color.White)
                )
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        AsyncImage(
                            model = teamA?.teamImageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(50.dp)
                                .aspectRatio(1f, matchHeightConstraintsFirst = true),
                            placeholder = painterResource(id = R.drawable.ic_menu_fixture)
                        )
                    }
                    Row {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = daysState.value,
                                style = timerTextStyle
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "DAYS",
                                style = timerFooterStyle
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = hoursState.value,
                                style = timerTextStyle
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "HRS",
                                style = timerFooterStyle
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = minutesState.value,
                                style = timerTextStyle
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "MINS",
                                style = timerFooterStyle
                            )
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = secondsState.value,
                                style = timerTextStyle
                            )
                            Spacer(modifier = Modifier.height(5.dp))
                            Text(
                                text = "SECS",
                                style = timerFooterStyle
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 10.dp)
                    ) {
                        AsyncImage(
                            model = teamB?.teamImageUrl,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(50.dp)
                                .aspectRatio(1f, matchHeightConstraintsFirst = true),
                            placeholder = painterResource(id = R.drawable.ic_menu_fixture)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Row(
                    modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = data?.venueName ?: "", style = matchStatusTextStyle
                    )
                }
                if (sponsorLogo!=null && isSponsorLogoRequired){
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                    Row(
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .fillMaxWidth()
                    ) {
                        Text(text = "Powered by", style = poweredByStyle)
                        Image(
                            painterResource(upcomingLogo),
                            contentDescription = "",
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(30.dp)
                                .background(Color.Black)
                        )
                    }
                    Spacer(
                        modifier = Modifier
                            .padding(vertical = 10.dp)
                            .height(1.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }

            }
        }
    }
}

@Composable
@Preview(showBackground = true, showSystemUi = true)
fun UpcomingMatchCardTypeTwoPreview(modifier: Modifier = Modifier) {
    UpcomingMatchCardTypeTwo(
        data = null,
        isSponsorLogoRequired=true,
        sponsorLogo = R.drawable.ic_upcoming,
        cardBackGroundColor = R.color.blue
    ){

    }
}
