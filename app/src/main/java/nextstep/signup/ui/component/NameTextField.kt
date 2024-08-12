package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import nextstep.signup.R
import nextstep.signup.core.validation.NameValidationResult
import nextstep.signup.ui.ThemePreviews

@Composable
internal fun NameTextField(
    userName: String,
    nameValidationResult: NameValidationResult,
    onUserNameChange: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    val supportingText: @Composable (() -> Unit)? = remember(userName, nameValidationResult) {
        if (userName.isEmpty()) return@remember null
        when (nameValidationResult) {
            NameValidationResult.VALID -> null
            NameValidationResult.LENGTH_ERROR -> {
                { Text(text = stringResource(id = R.string.signup_name_length_error)) }
            }

            NameValidationResult.CHARACTER_ERROR -> {
                { Text(text = stringResource(id = R.string.signup_name_character_error)) }
            }
        }
    }

    SignUpTextField(
        modifier = modifier,
        value = userName,
        onValueChange = onUserNameChange,
        label = { Text(text = stringResource(id = R.string.signup_username)) },
        supportingText = supportingText,
    )
}

private class NameTextFieldPreviewParameterProvider : PreviewParameterProvider<Pair<String, NameValidationResult>> {
    override val values = sequenceOf(
        "" to NameValidationResult.VALID,
        "A" to NameValidationResult.LENGTH_ERROR,
        "JohnDoe!" to NameValidationResult.CHARACTER_ERROR,
        "John Doe" to NameValidationResult.VALID
    )
}

@ThemePreviews
@Composable
private fun PreviewNameTextField(
    @PreviewParameter(NameTextFieldPreviewParameterProvider::class) nameData: Pair<String, NameValidationResult>
) {
    NameTextField(
        userName = nameData.first,
        nameValidationResult = nameData.second,
        onUserNameChange = {},
        modifier = Modifier
    )
}
