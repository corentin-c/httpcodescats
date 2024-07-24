package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme

@Composable
fun HttpCodeListScreen(
	viewModel: HttpCodesListViewModel = hiltViewModel(),
	onHttpCodeClicked: (code: Int) -> Unit
) {
	val uiState = viewModel.uiState.collectAsState().value
	HttpCodeListScreenContent(httpCodes = uiState.httpCodes,
		onHttpCodeClicked = onHttpCodeClicked)
}

@Composable
fun HttpCodeListScreenContent(
	httpCodes: List<HttpCode>?,
	onHttpCodeClicked: (code: Int) -> Unit
) {
	if (httpCodes != null) {
		if (httpCodes.isNotEmpty()) {
			LazyVerticalStaggeredGrid(
				modifier = Modifier.fillMaxSize(),
				columns = StaggeredGridCells.Adaptive(128.dp),
				contentPadding = PaddingValues(
					start = 12.dp,
					top = 16.dp,
					end = 12.dp,
					bottom = 16.dp
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
								.padding(4.dp),
						) {
							Column(
								modifier = Modifier
									.fillMaxSize()
									.padding(4.dp),
								horizontalAlignment = Alignment.CenterHorizontally
							) {
								Text(text = httpCode.code.toString(), textAlign = TextAlign.Center)
								Text(text = httpCode.name, textAlign = TextAlign.Center)
							}
						}
					}
				}
			)
		} else {
			Text(
				modifier = Modifier.fillMaxSize(),
				text = "Http codes retrieved but empty :("
			)
		}

	} else {
		Text(
			modifier = Modifier.fillMaxSize(),
			text = "Could not retrieve Http codes :("
		)
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewWithContent() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			httpCodes = HttpCode.httpCodes,
			onHttpCodeClicked = {
				// empty
			}
		)
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewNull() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			httpCodes = null,
			onHttpCodeClicked = {
				// empty
			}
		)
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewEmpty() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			httpCodes = emptyList(),
			onHttpCodeClicked = {
				// empty
			}
		)
	}
}