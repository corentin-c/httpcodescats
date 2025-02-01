package com.github.corentinc.httpcodescats.ui.screens.httpcodedetails

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.github.corentinc.httpcodescats.R
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.ui.LinkText
import com.github.corentinc.httpcodescats.ui.LinkTextData
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme


@Composable
fun HttpCodeDetailsScreen(
	code: Int, viewModel: HttpCodeDetailViewModel = hiltViewModel()
) {
	LaunchedEffect(key1 = Unit) {
		viewModel.getHttpCodeDetails(code = code)
	}
	val uiState = viewModel.uiState.collectAsState().value

	HttpCodeDetailsContent(
		uiState.isLoading,
		uiState.httpCode
	)

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HttpCodeDetailsContent(
	isLoading: Boolean,
	httpCode: HttpCode?
) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(4.dp),
		verticalArrangement = Arrangement.SpaceEvenly,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		if (isLoading) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier
					.fillMaxSize()
			) {
				CircularProgressIndicator()
			}
		} else {
			httpCode!! // always not null when loading is done
			GlideImage(
				model = httpCode.imageUrl,
				loading = placeholder(R.drawable.cat_loader),
				contentScale = ContentScale.FillWidth,
				contentDescription = "Cat image for http code ${httpCode.code}",
				modifier = Modifier
					.padding(12.dp)
			)
			Column(
				modifier = Modifier
					.padding(4.dp),
				horizontalAlignment = Alignment.CenterHorizontally
			) {
				Text(
					text = "HTTP status code : ",
					textDecoration = TextDecoration.Underline,
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.code.toString(),
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = "Name : ",
					textAlign = TextAlign.Center,
					textDecoration = TextDecoration.Underline,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.name,
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = "Description : ",
					textDecoration = TextDecoration.Underline,
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.description,
					textAlign = TextAlign.Center,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				LinkText(
					linkTextData = listOf(
						LinkTextData(
							text = "Open source in browser",
							tag = "source",
							annotation = httpCode.source,
						)
					)
				)

			}
		}

	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeDetailsPreview() {
	HttpCodesCatsTheme {
		HttpCodeDetailsContent(
			isLoading = false,
			HttpCode(
				101,
				"Test",
				"Super very long description that will probably take more than a line"
			)
		)
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeDetailsPreviewLoading() {
	HttpCodesCatsTheme {
		HttpCodeDetailsContent(
			isLoading = true,
			HttpCode(
				101,
				"Test",
				"Super very long description that will probably take more than a line"
			)
		)
	}
}