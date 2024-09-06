package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme

const val EMAIL_REGEX = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"

@Composable
fun EmailTextField(email: MutableState<String>) {
    val focusManager = LocalFocusManager.current
    var isError by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
    ) {
        TextField(
            value = email.value,
            onValueChange = {
                email.value = it
                isError = it.isNotEmpty() && !it.matches(Regex(EMAIL_REGEX))
            },
            isError = isError,
            label = {
                Text(text = stringResource(id = R.string.signup_email))
            },
            modifier = Modifier
                .fillMaxWidth(1.0F)
                .testTag("emailTextField"),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                autoCorrect = false,
                keyboardType = KeyboardType.Email
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.moveFocus(FocusDirection.Next)
                }
            )
        )
        Box(
            modifier = Modifier
                .padding(start = 16.dp)
                .height(32.dp)
        ) {
            if (isError) {
                Text(
                    text = stringResource(id = R.string.signup_error_email),
                    color = androidx.compose.material3.MaterialTheme.colorScheme.error,
                    fontSize = 12.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EmailTextFieldPreview() {
    val input = remember { mutableStateOf("") }

    SignupTheme {
        EmailTextField(email = input)
    }
}