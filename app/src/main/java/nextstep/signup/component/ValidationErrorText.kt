package nextstep.signup.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import nextstep.signup.util.validation.ValidationErrorType
import nextstep.signup.util.validation.ValidationResult

@Composable
fun ValidationErrorText(
    validationResult: ValidationResult,
    modifier: Modifier = Modifier,
    lengthErrorResId: Int? = null,
    regexErrorResId: Int? = null,
    equalityErrorResId: Int? = null,
) {
    val text = validationResult.getErrorType()?.let { type ->
        when (type) {
            ValidationErrorType.LengthError -> lengthErrorResId?.let { stringResource(id = it) } ?: ""
            ValidationErrorType.RegexError -> regexErrorResId?.let { stringResource(id = it) } ?: ""
            ValidationErrorType.EqualityError -> equalityErrorResId?.let { stringResource(id = it) } ?: ""
        }
    } ?: ""
    Text(
        modifier = modifier,
        text = text
    )
}