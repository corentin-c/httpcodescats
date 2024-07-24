package com.github.corentinc.httpcodescats

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.github.corentinc.httpcodescats.ui.navigation.NavigationController
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme

class MainActivity : ComponentActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		enableEdgeToEdge()
		setContent {
			HttpCodesCatsTheme {
				NavigationController()
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
	HttpCodesCatsTheme {
		NavigationController()
	}
}