package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

const val USERNAME_REGEX = "^[a-zA-Z가-힣]+$"

@Composable
fun UserNameInput(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val errorMessageResId = getUserNameErrorMessage(value)

    TextField(
        value = value,
        onValueChange = onValueChange,
        maxLines = 1,
        isError = errorMessageResId != null,
        supportingText = {
            if (errorMessageResId != null) {
                Text(text = stringResource(id = errorMessageResId))
            }
        },
        label = { Text(text = stringResource(id = R.string.userNameLabel)) },
        modifier = modifier
    )
}

fun getUserNameErrorMessage(value: String): Int? {
    return when {
        value.isEmpty() -> null
        !value.matches(Regex(USERNAME_REGEX)) -> R.string.userNameInvalidCharactersMessage
        value.length !in 2..5 -> R.string.userNameLengthValidationMessage
        else -> null
    }
}

@Preview
@Composable
private fun UserNameInputPreview() {
    var userName by remember { mutableStateOf("") }

    SignupTheme {
        UserNameInput(
            value = userName,
            onValueChange = {
                userName = it
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        )
    }
}