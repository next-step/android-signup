package nextstep.signup.screen

import androidx.compose.runtime.Composable
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.core.validation.Validator

data class InputFieldModel(
    val value: String,
    val onValueChange: (String) -> Unit,
    val validator: Validator,
    val label: @Composable () -> Unit,
    val visualTransformation: VisualTransformation = VisualTransformation.None
)