package nextstep.signup.ui.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.input.VisualTransformation
import nextstep.signup.core.validation.Validator

@Stable
data class InputFieldModel(
    val value: String,
    val onValueChange: (String) -> Unit,
    val validator: Validator,
    val label: @Composable () -> Unit,
    val visualTransformation: VisualTransformation = VisualTransformation.None
)