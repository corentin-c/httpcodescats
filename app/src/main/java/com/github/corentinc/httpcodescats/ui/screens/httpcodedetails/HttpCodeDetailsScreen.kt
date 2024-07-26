package com.github.corentinc.httpcodescats.ui.screens.httpcodedetails

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
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
fun HttpCodeDetailsScreen(
	code: Int, viewModel: HttpCodeDetailViewModel = hiltViewModel()
) {
	LaunchedEffect(key1 = Unit) {
		viewModel.getHttpCodeDetails(code = code)
	}
	val uiState = viewModel.uiState.collectAsState().value

	HttpCodeDetailsContent(
		uiState.httpCode
	)

}

@OptIn(ExperimentalGlideComposeApi::class)
@Composable
fun HttpCodeDetailsContent(
	httpCode: HttpCode?
) {
	val context = LocalContext.current
	Column(
		modifier = Modifier
			.fillMaxSize()
			.padding(4.dp),
		verticalArrangement = Arrangement.SpaceEvenly,
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		httpCode?.let {
			GlideImage(
				model = httpCode.imageUrl,
				loading = placeholder(R.drawable.ic_launcher_foreground),
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
					text = "HTTP code : ",
					textDecoration = TextDecoration.Underline,
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.code.toString(),
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = "Name : ",
					textAlign = TextAlign.Center,
					textDecoration = TextDecoration.Underline,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.name,
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				Text(
					text = "Description : ",
					textDecoration = TextDecoration.Underline,
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.titleMedium
				)
				Text(
					text = httpCode.description,
					textAlign = TextAlign.Center,
					color = MaterialTheme.colorScheme.onBackground,
					style = MaterialTheme.typography.bodyMedium
				)
				Spacer(modifier = Modifier.height(12.dp))
				LinkText(
					linkTextData = listOf(
						LinkTextData(
							text = "Open source in browser",
							tag = "source",
							annotation = httpCode.source,
							onClick = { it ->
								val intent = CustomTabsIntent.Builder()
									.build()
								intent.launchUrl(context, Uri.parse(it.item))
							},
						)
					)
				)

			}
		} ?: run {

		}
	}
}

@Preview(showBackground = true)
@Composable
fun HttpCodeDetailsPreview() {
	HttpCodesCatsTheme {
		HttpCodeDetailsContent(
			HttpCode(
				101,
				"Test",
				"Super very long description that will probably take more than a line"
			)
		)
	}
}

data class LinkTextData(
	val text: String,
	val tag: String? = null,
	val annotation: String? = null,
	val onClick: ((str: AnnotatedString.Range<String>) -> Unit)? = null,
)

@Composable
fun LinkText(
	linkTextData: List<LinkTextData>,
	modifier: Modifier = Modifier,
) {
	val annotatedString = createAnnotatedString(linkTextData)

	ClickableText(
		text = annotatedString,
		style = MaterialTheme.typography.bodyLarge,
		onClick = { offset ->
			linkTextData.forEach { annotatedStringData ->
				if (annotatedStringData.tag != null && annotatedStringData.annotation != null) {
					annotatedString.getStringAnnotations(
						tag = annotatedStringData.tag,
						start = offset,
						end = offset,
					).firstOrNull()?.let {
						annotatedStringData.onClick?.invoke(it)
					}
				}
			}
		},
		modifier = modifier,
	)
}

@Composable
private fun createAnnotatedString(data: List<LinkTextData>): AnnotatedString {
	return buildAnnotatedString {
		data.forEach { linkTextData ->
			if (linkTextData.tag != null && linkTextData.annotation != null) {
				pushStringAnnotation(
					tag = linkTextData.tag,
					annotation = linkTextData.annotation,
				)
				withStyle(
					style = SpanStyle(
						color = MaterialTheme.colorScheme.primary,
						textDecoration = TextDecoration.Underline,
					),
				) {
					append(linkTextData.text)
				}
				pop()
			} else {
				append(linkTextData.text)
			}
		}
	}
}