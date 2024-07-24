package com.github.corentinc.httpcodescats.ui.screens.httpcodedetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
fun HttpCodeDetailsScreen(
	code: Int,
	viewModel: HttpCodeDetailViewModel = hiltViewModel()
) {
	LaunchedEffect(key1 = Unit) {
		viewModel.getHttpCodeDetails(code = code)
	}
	val uiState = viewModel.uiState.collectAsState().value

	HttpCodeDetailsContent(
		uiState.httpCode
	)

}

@Composable
fun HttpCodeDetailsContent(
	httpCode: HttpCode?
) {
	Column {
		Column(
			modifier = Modifier
				.fillMaxSize()
				.padding(4.dp),
			horizontalAlignment = Alignment.CenterHorizontally
		) {
			Text(text = httpCode?.code.toString(), textAlign = TextAlign.Center)
			Text(text = httpCode?.name.toString(), textAlign = TextAlign.Center)
		}
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeDetailsPreview() {
	HttpCodesCatsTheme {
		HttpCodeDetailsContent(HttpCode(101, "Test", "Test"))
	}
}