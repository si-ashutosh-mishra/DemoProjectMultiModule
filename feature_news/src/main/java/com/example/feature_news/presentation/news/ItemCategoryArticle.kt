package com.example.feature_news.presentation.news

import androidx.annotation.ColorRes
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.feature_news.R
import com.example.feature_news.presentation.news.typeone.ItemCategoryArticleOne
import com.example.feature_news.presentation.news.typetwo.ItemCategoryArticleTwo
import com.example.feature_news.presentation.news.typetwo.LazyColumnWithMultipleItem
import com.example.feature_news.presentation.theme.light_gray
import com.example.feature_news.presentation.theme.purple
import com.example.feature_news.presentation.theme.textcolor_gray
import com.example.lb_content_listing.business.domain.model.AssetItem

@Composable
fun ItemCategoryArticle(
    title: String,
    titleFontSize: TextUnit,
    titleFontStyle: FontFamily,
    assetList: List<AssetItem>,
    clickViewAll: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 15.dp, end = 15.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = title.uppercase(),
            fontSize = titleFontSize,
            fontFamily = titleFontStyle
        )

        Text(text = "View All".uppercase(), fontSize = 13.sp, modifier = Modifier.clickable {
            clickViewAll()
        })
    }
    Spacer(
        modifier = Modifier
            .fillMaxWidth()
            .height(15.dp)
    )

    ItemCategoryArticleOne(
        assetList = assetList
    )
}