package com.github.corentinc.httpcodescats.ui

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.TextSelectionColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp

data class ClassicFieldExtraParameters(
	val keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
	val keyboardActions: KeyboardActions = KeyboardActions(),
	val visualTransformation: VisualTransformation = VisualTransformation.None,
	val trailingIcon: @Composable (() -> Unit)? = null
)

@Composable
fun ClassicField(
	modifier: Modifier = Modifier,
	placeholder: String,
	value: String,
	onValueChange: (String) -> Unit,
	enabled: Boolean = true,
	classicFieldExtraParameters: ClassicFieldExtraParameters
) {
	TextField(
		modifier = modifier,
		enabled = enabled,
		shape = RoundedCornerShape(5.dp),
		trailingIcon = classicFieldExtraParameters.trailingIcon,
		value = value,
		onValueChange = onValueChange,
		visualTransformation = classicFieldExtraParameters.visualTransformation,
		keyboardOptions = classicFieldExtraParameters.keyboardOptions,
		keyboardActions = classicFieldExtraParameters.keyboardActions,
		placeholder = {
			Text(
				placeholder
			)
		}
	)
}
