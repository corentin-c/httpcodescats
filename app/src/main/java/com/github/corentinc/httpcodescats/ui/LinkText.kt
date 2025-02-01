package com.github.corentinc.httpcodescats.ui

import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.text.BasicText
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle


data class LinkTextData(
	val text: String,
	val tag: String? = null,
	val annotation: String? = null
)

@Composable
fun LinkText(
	linkTextData: List<LinkTextData>,
	modifier: Modifier = Modifier,
) {
	val context = LocalContext.current
	val annotatedString = createAnnotatedString(linkTextData)

    val layoutResult = remember { mutableStateOf<TextLayoutResult?>(null) }
    val onClick : (offSet: Int) -> Unit = { offset ->
        linkTextData.forEach { annotatedStringData ->
            if (annotatedStringData.tag != null && annotatedStringData.annotation != null) {
                annotatedString.getStringAnnotations(
                    tag = annotatedStringData.tag,
                    start = offset,
                    end = offset,
                ).firstOrNull()?.let {
					val intent = CustomTabsIntent.Builder()
						.build()
					intent.launchUrl(context, Uri.parse(it.item))
                }
            }
        }
    }

    val pressIndicator = Modifier.pointerInput(onClick) {
        detectTapGestures { pos ->
            layoutResult.value?.let { layoutResult ->
                onClick(layoutResult.getOffsetForPosition(pos))
            }
        }
    }

    BasicText(
        text = annotatedString,
        modifier = modifier.then(pressIndicator),
        style = MaterialTheme.typography.bodyLarge,
        onTextLayout = {
            layoutResult.value = it
        }
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
				withStyle(
					style = SpanStyle(
						color = LocalContentColor.current,
					),
				) {
					append(linkTextData.text)
				}
			}
		}
	}
}