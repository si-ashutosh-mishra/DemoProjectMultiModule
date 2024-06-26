package com.knightclub.app.data.model.layoutbuilder

import com.google.gson.annotations.SerializedName
import com.knightclub.app.data.model.loyalty.BannerRedirectionModel

data class MetaInfo(
    @SerializedName("article_description_tag")
    val articleDescriptionTag: String?,
    @SerializedName("article_description_tag_attributes")
    val articleDescriptionTagAttributes: String?,
    @SerializedName("article_title_tag")
    val articleTitleTag: String?,
    @SerializedName("article_title_tag_attributes")
    val articleTitleTagAttributes: String?,
    @SerializedName("banner_height")
    val bannerHeight: Int?,
    @SerializedName("banner_image")
    val bannerImage: String?,
    @SerializedName("banner_link")
    val bannerLink: String?,
    @SerializedName("ad_unit_id")
    val adUnitId: String?,
    @SerializedName("ad_width")
    val ad_width: Int?,
    @SerializedName("ad_height")
    val ad_height: Int?,
    @SerializedName("ad_ratio")
    val adRatio: Double?,
    @SerializedName("banner_width")
    val bannerWidth: Int?,
    @SerializedName("component")
    val component: String?,
//    @SerializedName("extraData")
//    val extraData: String?,
    @SerializedName("fb")
    val fb: Int?,
    @SerializedName("imgRatio")
    val imgRatio: String?,
    @SerializedName("is_comments")
    val isComments: Boolean?,
    @SerializedName("is_reactions")
    val isReactions: Boolean?,
    @SerializedName("is_share")
    val isShare: Boolean?,
    @SerializedName("order")
    val order: Int?,
    @SerializedName("show_author")
    val showAuthor: Int?,
    @SerializedName("show_category")
    val showCategory: Int?,
    @SerializedName("show_date")
    val showDate: Int?,
    @SerializedName("show_description")
    val showDescription: Int?,
    @SerializedName("show_image")
    val showImage: Boolean?,
    @SerializedName("show_image_count")
    val showImageCount: Boolean?,
    @SerializedName("show_item_icon")
    val showItemIcon: Int?,
    @SerializedName("show_tagline")
    val showTagline: Boolean?,
    @SerializedName("show_timestamp")
    val showTimestamp: Boolean?,
    @SerializedName("show_title")
    val showTitle: Int?,
    @SerializedName("show_widget_title")
    val showWidgetTitle: Int?,
    @SerializedName("showitem_icon_content")
    val showitemIconContent: Int?,
    @SerializedName("view")
    val view: String?,
    @SerializedName("widget_title_tag")
    val widgetTitleTag: String?,
    @SerializedName("widget_title_tag_attributes")
    val widgetTitleTagAttributes: String?,
    @SerializedName("api_config")
    val apiConfig: ApiConfig?,
    @SerializedName("browser_title")
    val browserTitle: Any?,
    @SerializedName("canonical_url")
    val canonicalUrl: String?,
    @SerializedName("keywords")
    val keywords: Any?,
    @SerializedName("meta_desc")
    val metaDesc: Any?,
    @SerializedName("widget_order")
    val widgetOrder: Int?,
    @SerializedName("layout_data")
    val layoutData: List<LayoutData>?,

    @SerializedName("banner_data")
    val bannerData: List<Banner>?,
    @SerializedName("series_id")
    val seriesId: Int?,
    @SerializedName("more_links")
    val moreLinks: MoreLinks?,

    // KKR Shop WebView
    @SerializedName("webview_url")
    val webViewUrl: String?,
    @SerializedName("webview_height")
    val webViewHeight: String?,
    @SerializedName("webview_width")
    val webViewWidth: String?,

    // Changes on KKR V2
    @SerializedName("show_more_text")
    val showMoreText: Int?,
    @SerializedName("more_text")
    val moreText: String?,
    @SerializedName("is_carousel")
    val isCarousel: Boolean?,
    @SerializedName("banner_carousel_info", alternate = ["banner_carousel_data"])
    val bannerCarouselInfo: List<BannerRedirectionModel>?,
    @SerializedName("banner_info")
    val bannerInfo: BannerRedirectionModel?,
    @SerializedName("dependency")
    val dependency: MetaInfo?


)

data class MoreLinks(
    @SerializedName("more")
    val morePath: String?
)