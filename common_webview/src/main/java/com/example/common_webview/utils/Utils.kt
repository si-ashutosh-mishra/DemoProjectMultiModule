package com.example.common_webview.utils

import android.net.Uri

object Utils {
    fun addQueryParameter(webViewUrl: String,timeStamp:Boolean,timeStampQuery:String? = null): String? {
        val uri: Uri = Uri.parse(webViewUrl)
        val queryName = uri.queryParameterNames
        var url = if (queryName.isNullOrEmpty()) {
            if (timeStamp) {
                "$webViewUrl?webview=true&platform_type=2${timeStampQuery}"
            } else {
                "$webViewUrl?webview=true&platform_type=2"
            }
        } else if (uri.getQueryParameter("webview") == null) {
            if (timeStamp) {
                "$webViewUrl?webview=true&platform_type=2${timeStampQuery}"
            } else {
                "$webViewUrl?webview=true&platform_type=2"
            }
        } else if (uri.getQueryParameter("webview") != null) {
            if (timeStamp) {
                "$webViewUrl&platform_type=2${timeStampQuery}"
            } else {
                "$webViewUrl&platform_type=2"
            }
        } else {
            webViewUrl
        }
        return url
    }
}