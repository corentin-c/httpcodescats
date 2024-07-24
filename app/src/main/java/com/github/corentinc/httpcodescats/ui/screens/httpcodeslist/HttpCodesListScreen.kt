package com.github.corentinc.httpcodescats.ui.screens.httpcodeslist

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.github.corentinc.httpcodescats.ui.theme.HttpCodesCatsTheme

@Composable
fun HttpCodeListScreen(
	viewModel: HttpCodesListViewModel = hiltViewModel()
) {
	LazyVerticalGrid(
		modifier = Modifier.fillMaxSize(),
		columns = GridCells.Adaptive(128.dp),
		contentPadding = PaddingValues(
			start = 12.dp,
			top = 16.dp,
			end = 12.dp,
			bottom = 16.dp
		),
		content = {
			items(5) { index ->
				Card(
					modifier = Modifier
						.padding(4.dp)
						.fillMaxWidth(),
				) {
					Text("Kappa")
				}
			}
		}
	)
}

@Preview(showBackground = true)
@Composable
fun HttpCodeListScreenPreview() {
	HttpCodesCatsTheme {
		HttpCodeListScreen()
	}
}