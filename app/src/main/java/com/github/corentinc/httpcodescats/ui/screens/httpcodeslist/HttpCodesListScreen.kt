@file:OptIn(ExperimentalGlideComposeApi::class)

package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi
import com.bumptech.glide.integration.compose.GlideImage
import com.bumptech.glide.integration.compose.placeholder
import com.github.corentinc.httpcodescats.R
import com.github.corentinc.httpcodescats.model.HttpCode
import com.github.corentinc.httpcodescats.ui.ClassicField
import com.github.corentinc.httpcodescats.ui.ClassicFieldExtraParameters
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme

@Composable
fun HttpCodeListScreen(
	viewModel: HttpCodesListViewModel = hiltViewModel(),
	onHttpCodeClicked: (code: Int) -> Unit,
	onAboutClicked: () -> Unit
) {
	val uiState = viewModel.uiState.collectAsState().value

	HttpCodeListScreenContent(
		isLoading = uiState.isLoading,
		httpCodes = uiState.httpCodes,
		filter = uiState.filter,
		onHttpCodeClicked = onHttpCodeClicked,
		onAboutClicked = onAboutClicked,
		onFilterChanged = { newFilter ->
			viewModel.onFilterChanged(newFilter)
		}
	)
}

@Composable
fun HttpCodeListScreenContent(
	isLoading: Boolean,
	httpCodes: List<HttpCode>,
	filter: String = "",
	onHttpCodeClicked: (code: Int) -> Unit,
	onAboutClicked: () -> Unit,
	onFilterChanged: (newFilter: String) -> Unit
) {
	Column(
		verticalArrangement = Arrangement.Center,
		horizontalAlignment = Alignment.CenterHorizontally,
		modifier = Modifier
			.fillMaxSize()
	) {
		Row(
			horizontalArrangement = Arrangement.SpaceEvenly,
			verticalAlignment = Alignment.CenterVertically
		) {
			ClassicField(
				modifier = Modifier.padding(12.dp),
				placeholder = "Search for a code or a word",
				value = filter,
				onValueChange = { value ->
					onFilterChanged(value)
				},
				enabled = !isLoading,
				classicFieldExtraParameters = ClassicFieldExtraParameters(
					keyboardOptions = KeyboardOptions(
						keyboardType = KeyboardType.Text
					),
					trailingIcon = {
						Icon(
							imageVector = Icons.Default.Search,
							contentDescription = "Search for http status code"
						)
					}),
			)
			Icon(
				modifier = Modifier
					.clickable {
						onAboutClicked()
					}
					.width(30.dp)
					.height(30.dp),
				imageVector = Icons.Default.Info,
				tint = MaterialTheme.colorScheme.primary,
				contentDescription = "About the app"
			)
		}
		if (isLoading) {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.fillMaxSize()
			) {
				CircularProgressIndicator(
				)
			}
		} else {
			Column(
				verticalArrangement = Arrangement.Center,
				horizontalAlignment = Alignment.CenterHorizontally,
				modifier = Modifier.fillMaxSize()
			) {
				if (httpCodes.isNotEmpty()) {
					LazyVerticalStaggeredGrid(
						modifier = Modifier
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
											loading = placeholder(R.drawable.cat_loader),
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
						text = "No corresponding HTTP status code, search for something else like \"400\" !",
						textAlign = TextAlign.Center
					)
				}
			}
		}
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreviewWithContent() {
	HttpCodesCatsTheme {
		HttpCodeListScreenContent(
			isLoading = false,
			httpCodes = listOf(
				HttpCode(
					100,
					"Continue",
					"This interim response indicates that the client should continue the request or ignore the response if the request is already finished."
				),
				HttpCode(
					101,
					"Switching Protocols",
					"This code is sent in response to an Upgrade request header from the client and indicates the protocol the server is switching to."
				),
				HttpCode(
					102,
					"Processing",
					"This code indicates that the server has received and is processing the request, but no response is available yet."
				)
			),
			onHttpCodeClicked = {
				// empty
			},
			onFilterChanged = {
				// empty
			},
			onAboutClicked = {
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
			},
			onFilterChanged = {
				// empty
			},
			onAboutClicked = {
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
			},
			onFilterChanged = {
				// empty
			},
			onAboutClicked = {
				// empty
			})
	}
}