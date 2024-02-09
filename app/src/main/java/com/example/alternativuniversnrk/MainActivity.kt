package com.example.alternativuniversnrk

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.ViewModelProvider
import com.example.alternativuniversnrk.ui.theme.AlternativuniversNrkTheme

class MainActivity : ComponentActivity() {

    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        super.onCreate(savedInstanceState)
        setContent {
            AlternativuniversNrkTheme {
                ContentFeed(
                    state = viewModel._state,
                    onClick = { url -> openUrlInBrowser(url, this) })
            }
        }
    }
}

fun openUrlInBrowser(url: String, context: Context) {
    val intent = Intent(Intent.ACTION_VIEW).apply {
        data = Uri.parse(url)
    }

    try {
        startActivity(context, intent, null)
    } catch (e: ActivityNotFoundException) {
        println("Error: $e") //TODO: lage noe user feedback for errors?
    } catch (e: Exception) {
        println("Error $e")
    }
}

