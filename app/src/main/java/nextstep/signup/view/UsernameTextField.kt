package nextstep.signup.view

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import nextstep.signup.R
import nextstep.signup.model.TextFieldState
import nextstep.signup.model.TextFieldType
import nextstep.signup.model.setMessage
import nextstep.signup.view.ui.theme.Dimens.TextHelper
import nextstep.signup.view.ui.theme.Red50
import nextstep.signup.view.ui.theme.SignupTheme

@Composable
fun UsernameContent(
    input: String,
    onValueChange: (String) -> Unit,
    validationState: TextFieldState,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier,
        value = input,
        onValueChange = onValueChange,
        label = { Text(text = stringResource(id = R.string.username)) },
        colors = TextFieldDefaults.colors(
            focusedLabelColor = if (validationState == TextFieldState.Valid || validationState == TextFieldState.Default) {
                MaterialTheme.colorScheme.primary
            } else {
                Red50
            }
        ),
        supportingText = {
            Text(
                text = setMessage(validationState, TextFieldType.Username),
                fontSize = TextHelper,
                color = Red50,
                textAlign = TextAlign.Start,
            )
        },
    )
}

@Preview(showBackground = true)
@Composable
private fun UsernameTextFieldPreview() {
    SignupTheme {
        UsernameContent(
            input = "",
            onValueChange = {},
            validationState = TextFieldState.Default,
            modifier = Modifier
        )
    }
}