package com.parsuomash.workspace

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.multiplatform.webview.web.WebView
import com.multiplatform.webview.web.rememberSaveableWebViewState
import com.multiplatform.webview.web.rememberWebViewNavigator

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val url = "http://192.168.1.85:8080/"

        setContent {
            val webViewState = rememberSaveableWebViewState(url)
            val webViewNavigator = rememberWebViewNavigator()

            LaunchedEffect(webViewNavigator) {
                val bundle = webViewState.viewState
                if (bundle == null) {
                    // This is the first time load, so load the home page.
                    webViewNavigator.loadUrl(url)
                }
            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color(0xFF152132))
                    .systemBarsPadding()
                    .imePadding(),
            ) {
                WebView(
                    modifier = Modifier.fillMaxSize(),
                    state = webViewState,
                    navigator = webViewNavigator,
                )
            }
        }
    }
}
