package com.example.common_webview.presentation

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import com.example.common_webview.R


class CustomChromeTabIntent {

    fun openCustomTab(
        context: Context,
        url: String,
    ) {
        val activity = (context as? Activity)
        val builder = CustomTabsIntent.Builder()
            .setDefaultColorSchemeParams(
                CustomTabColorSchemeParams.Builder().setToolbarColor(ContextCompat.getColor(context, R.color.white)).build()
            ).build()

        if (url.isNotEmpty()){
            val uri = Uri.parse(url)
            val packageName = "com.android.chrome"
            builder.intent.setPackage(packageName)
            try {
                builder.launchUrl(context, uri)
            } catch (e: Exception) {
                try {
                    activity?.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                } catch (e: Exception) {}
            }
        }

    }

}