package com.example.common_webview.presentation

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Context
import android.graphics.Bitmap
import android.util.Log
import android.webkit.JavascriptInterface
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import com.example.common_webview.utils.Utils

@Composable
@SuppressLint("SetJavaScriptEnabled")
fun WebViewHeaderScreen(
    title:String? = null,
    webUrl:String,
    timeStamp:Boolean = false,
    timeStampQuery:String? = null,
    titleBarIconTintColor: Color = Color.White,
    toolBarBGColor: Color = Color.Blue,
    toolBarTitleTextStyle: TextStyle = TextStyle(
        color = Color.White,
        textAlign = TextAlign.Center,
        fontSize = 14.sp,
        fontWeight = FontWeight.Bold,
    ),
    onNativeLogin:()->Unit,
    uscWebview:String?="",
    auth:String?="",
    isLoggedIn:Boolean = false,
    urcWebview:String? = ""
) {
    val url = remember {
        mutableStateOf<String?>("")
    }
    val headers= remember {
        mutableStateOf<HashMap<String, String?>>(HashMap())
    }

    LaunchedEffect(key1 = Unit) {
        url.value = Utils.addQueryParameter(
            webViewUrl = webUrl,
            timeStamp = timeStamp,
            timeStampQuery = timeStampQuery
        )
        if (isLoggedIn){
            headers.value["uscwebview"] = uscWebview
            headers.value["auth"] = auth
            headers.value["setlogincookie"] = if (isLoggedIn) "1" else "0"
            headers.value["urcwebview"] = urcWebview
        }
    }

    Column {
        Toolbar(
            onBackClick = {  },
            onFilterClick = { },
            titleBarIconTintColor = titleBarIconTintColor,
            toolBarColor = toolBarBGColor,
            toolBarTitle = title?:"",
            toolBarTitleTextStyle = toolBarTitleTextStyle,
            showBack = true,
            showFilter = false
        )
        AndroidView(
            factory = { context ->
                WebView(context).apply {
                    settings.javaScriptEnabled = true
                    settings.domStorageEnabled = true
                    settings.userAgentString = System.getProperty("http.agent")
                    isClickable = true
                    settings.cacheMode = WebSettings.LOAD_NO_CACHE
                    webViewClient = CustomWebViewHeaderClient(timeStamp,timeStampQuery,headers.value)
                    addJavascriptInterface(WebViewHeaderScreenInterface(context,onNativeLogin),"mobileApp")
                }
            },
            update = { webView ->
                webView.loadUrl(url.value?:"",headers.value)
            }
        )

    }

}

class WebViewHeaderScreenInterface(private val mContext: Context, val  onNativeLogin: () -> Unit) {

    @JavascriptInterface
    fun nativeLogin() {
        onNativeLogin()
    }

}


class CustomWebViewHeaderClient(
    private val timeStamp: Boolean,
    private val timeStampQuery: String?,
    private val header: HashMap<String, String?>
): WebViewClient(){
    override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
        val webUrl = try {
            Utils.addQueryParameter(
                webViewUrl = url?:"",
                timeStamp = timeStamp,
                timeStampQuery = timeStampQuery
            )
        } catch (e: Exception) {
            url
        }
        Log.d("WebViewUrl", "shouldOverrideUrlLoading: " + webUrl)
        webUrl?.let {
            try {
                view?.loadUrl(it,header)
                //chromeTabIntent.openCustomTab(this@WebViewActivity, it)
            } catch (e: ActivityNotFoundException) {
                view?.loadUrl(it,header)
            } catch (_: Exception) {
                view?.loadUrl(it,header)
            }
        }
        return true
    }

    override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
        super.onPageStarted(view, url, favicon)
        //loader.show(true)
    }

    override fun onPageFinished(view: WebView?, url: String?) {
        super.onPageFinished(view, url)
        //loader.show(false)
    }
}