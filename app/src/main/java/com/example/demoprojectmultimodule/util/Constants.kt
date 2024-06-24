package com.example.demoprojectmultimodule.util

import android.os.Bundle

object Constants {
    const val NATIONALITY_ID_INDIA = "4"
    const val NATIONALITY_ID_USA = "20"
    const val NATIONALITY_ID_WI = "9"
    const val PAGE_SIZE = 6
    const val DETAIL_PAGE_LIST_SIZE = 4

    const val PLATFORM_TYPE_ANDROID = 2

    const val EVENT_TAG = "Android"

    const val EXTRA_TITLE_ALIAS = "title_alias"
    const val EXTRA_VIDEO_DETAIL_TYPE = "video_detail_type"
    const val EXTRA_IS_SELECTED = "is_selected"

    const val LOG_OUT_USER = "log_out_user"

    const val LOGOUT_REQUEST_KEY = "delete_restore_fragment"

    const val KC_DATA_STORE = "kc_data_store"
    const val USER_TOKEN = "usertoken"

    const val EVENT_ID = "event_id"
    const val EVENT_VALUE = "event_value"
    const val IS_WEBVIEW = "is_webview"
    const val IN_APP_BROWSER = "in_app_browser"
    const val IS_EXTERNAL_WEBVIEW = "is_external_webview"
    const val WEBVIEW_URL = "webview_url"

    const val EXTRA_ASSET_TYPE_ID = "asset_type_id"
    const val EXTRA_REEL_DEEP_LINK = "reel_deep_link"
    const val EXTRA_REEL_DEEP_LINK_ASSET_SLUG = "reel_deep_link_asset_slug"
    const val EXTRA_REEL_DEEP_LINK_ASSET_TYPE = "reel_deep_link_asset_type"
    const val EXTRA_REEL_DEEP_LINK_ASSET_ID = "reel_deep_link_asset_id"
    const val EXTRA_IS_FROM_GUEST_DIALOG = "is_from_guest_dialog"
    const val EXTRA_REFERRAL_CODE = "invitation_referral_code"
    const val EXTRA_ASSET_TITLE_ALIAS = "asset_title_alias"
    const val EXTRA_INCOMPLETE_DATA_PAYLOAD = "incomplete_data_payload"

    const val EXTRA_OPEN_EDIT_PROFILE = "open_edit_profile"

    const val VIDEO_DETAILS = "video_details"
    const val CURRENT_POSITION = "current_position"

    const val ASSET_TYPE = "asset_type"
    const val GUID = "guid"
    const val NOTIFICATION_MESSAGE_ID = "message_id"
    const val ASSET_TITLE = "asset_title"
    const val ASSET_ALIAS = "asset_alias"
    const val ASSET_URL = "asset_url"
    const val HOME_TEAM_FULL_NAME = "home_team_full_name"
    const val HOME_TEAM_SHORT_NAME = "home_team_short_name"
    const val AWAY_TEAM_FULL_NAME = "away_team_full_name"
    const val AWAY_TEAM_SHORT_NAME = "away_team_short_name"
    const val GAME_NAME = "game_name"
    const val GAME_URL = "game_url"
    const val REDIRECTION_URL = "redirection_url"

    // Debug
    // const val COMET_CHAT_APP_ID ="235406e6c87afe3b"
    // const val COMET_CHAT_AUTH_KEY ="f55deb3d495200606fc510ce0ff48a0883b40ed5"

    // Release
    // const val COMET_CHAT_APP_ID ="237832001fed2f48"
    // const val COMET_CHAT_AUTH_KEY ="4207b4df8463a4c587958408daa82640909a516e"

    const val COMET_CHAT_REGION ="us"
    const val JOIN_CLUB_REQUEST = "join_club_request"

    const val KKR = "KKR"
    const val LAKR = "LAKR"
    const val TKR = "TKR"
    const val ADKR = "ADKR"

    var notificationPayload : Bundle? = null
    var wvRedirectionPayload : String? = null
    var deepLinkAssetSlug : String? = null
    var deepLinkAssetTypeId : String? = null
    var deepLinkAssetId : String? = null
    var isNotificationRewarded : Boolean = false
}