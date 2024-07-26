@file:OptIn(ExperimentalGlideComposeApi::class)

package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.github.corentinc.httpcodescats.R
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme

@Composable
fun HttpCodeListScreen(
	viewModel: HttpCodesListViewModel = hiltViewModel(), onHttpCodeClicked: (code: Int) -> Unit
) {
	val uiState = viewModel.uiState.collectAsState().value
	LaunchedEffect(key1 = Unit) {
		viewModel.onStart()
	}
	HttpCodeListScreenContent(
		isLoading = uiState.isLoading,
		httpCodes = uiState.httpCodes,
		onHttpCodeClicked = onHttpCodeClicked
	)
}

@Composable
fun HttpCodeListScreenContent(
	isLoading: Boolean,
	httpCodes: List<HttpCode>,
	onHttpCodeClicked: (code: Int) -> Unit
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
		if (httpCodes.isNotEmpty()) {
			LazyVerticalStaggeredGrid(
				modifier = Modifier
					.background(MaterialTheme.colorScheme.background)
					.fillMaxSize(),
				columns = StaggeredGridCells.Adaptive(150.dp),
				contentPadding = PaddingValues(
					start = 12.dp, top = 16.dp, end = 12.dp, bottom = 16.dp
				),
				content = {
					items(httpCodes.size) { index ->
						val httpCode = httpCodes[index]
						Card(
							onClick = {
								onHttpCodeClicked(httpCode.code)
							},
							modifier = Modifier
								.fillMaxSize()
								.padding(8.dp),
						) {
							Column(
								modifier = Modifier
									.fillMaxSize()
									.padding(8.dp),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								GlideImage(
									model = httpCode.imageUrl,
									loading = placeholder(R.drawable.ic_launcher_foreground),
									contentScale = ContentScale.Crop,
									contentDescription = "Cat image for http code ${httpCode.code}",
									modifier = Modifier
										.fillMaxSize()
										.padding(12.dp)
										.fillMaxSize()
								)
								Text(
									text = httpCode.name,
									textAlign = TextAlign.Center,
									style = MaterialTheme.typography.titleSmall
								)
							}
						}
					}
				})
		} else {
			Text(
				modifier = Modifier.fillMaxSize(), text = "Http codes retrieved but empty :("
			)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewWithContent() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			isLoading = false,
			httpCodes = HttpCode.httpCodes,
			onHttpCodeClicked = {
				// empty
			})
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewLoading() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			isLoading = true,
			httpCodes = emptyList(),
			onHttpCodeClicked = {
				// empty
			})
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewEmpty() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			isLoading = false,
			httpCodes = emptyList(),
			onHttpCodeClicked = {
				// empty
			})
	}
}