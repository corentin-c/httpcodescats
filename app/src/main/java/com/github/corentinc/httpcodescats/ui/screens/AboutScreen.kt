package com.github.corentinc.httpcodescats.ui.screens

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.github.corentinc.httpcodescats.ui.LinkText
import com.github.corentinc.httpcodescats.ui.LinkTextData

@Composable
fun AboutScreen() {
	val context = LocalContext.current
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.padding(12.dp)
			.fillMaxSize()
			.verticalScroll(rememberScrollState())
	) {
		Text(
			text = "Hello catventurer !",
			style = MaterialTheme.typography.titleLarge
		)
		Spacer(modifier = Modifier.height(25.dp))
		Text(
			text = "This application is an educational app which goal is to learn HTTP status codes in a fun way using a beautiful cat picture for each of the codes.\n\n" +
					"With this app, you can see all available HTTP status codes, " +
					"search for them using the search bar on top of the list " +
					"and get details on each of them by clicking on the card.",
			style = MaterialTheme.typography.bodyLarge
		)
		Spacer(modifier = Modifier.height(25.dp))
		LinkText(
			linkTextData = listOf(
				LinkTextData(
					"The cat pictures used tor represent the HTTP status codes are based on "
				),
				LinkTextData(
					text = "HTTP Cats website",
					tag = "Create new scratch file from selection",
					annotation = "https://http.cat/",
					onClick = {
						val intent = CustomTabsIntent.Builder()
							.build()
						intent.launchUrl(context, Uri.parse(it.item))
					},
				),
				LinkTextData(". Thank you so much for this amazing website !")
			)
		)
		Spacer(modifier = Modifier.height(25.dp))
		LinkText(
			linkTextData = listOf(
				LinkTextData(
					"The descriptions of the codes are based on the very serious "
				),
				LinkTextData(
					text = "Mozilla developer website",
					tag = "Mozilla developer website",
					annotation = "https://developer.mozilla.org/en-US/docs/Web/HTTP/Status",
					onClick = {
						val intent = CustomTabsIntent.Builder()
							.build()
						intent.launchUrl(context, Uri.parse(it.item))
					},
				),
				LinkTextData(".")
			)
		)
		Spacer(modifier = Modifier.height(25.dp))
		LinkText(
			linkTextData = listOf(
				LinkTextData(
					"The entire code of this application is available on my "
				),
				LinkTextData(
					text = "Github",
					tag = "Github",
					annotation = "https://github.com/corentin-c/httpcodescats",
					onClick = {
						val intent = CustomTabsIntent.Builder()
							.build()
						intent.launchUrl(context, Uri.parse(it.item))
					},
				),
				LinkTextData(". "),
				LinkTextData(
					"It was made for my personal enjoyment on my free time. I decline all responsibility " +
							"if the information in this application is not correct."
				),
			)
		)
		Spacer(modifier = Modifier.height(25.dp))
		Text(
			text = "Enjoy :)",
			style = MaterialTheme.typography.bodyMedium
		)
	}
}

@Composable
@Preview(showBackground = true)
fun AboutScreenPreview() {
	AboutScreen()
}