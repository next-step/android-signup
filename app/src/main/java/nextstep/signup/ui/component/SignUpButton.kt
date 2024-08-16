package nextstep.signup.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.domain.SignUpValidator
import nextstep.signup.model.SignUpParams
import nextstep.signup.ui.theme.Blue50

const val SIGN_UP_BUTTON_TAG = "signUpButtonTag"

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    username: String,
    email: String,
    password: String,
    passwordConfirm: String,
    onClick: () -> Unit,
) {
    val enabled by remember(username, email, password, passwordConfirm) {
        mutableStateOf(
            SignUpValidator.valid(
                SignUpParams(
                    username,
                    email,
                    password,
                    passwordConfirm,
                )
            )
        )
    }

    Button(
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(containerColor = Blue50),
        enabled = enabled,
        modifier = modifier
            .height(50.dp)
            .testTag(SIGN_UP_BUTTON_TAG),
    ) {
        Text(text = stringResource(id = R.string.sign_up))
    }
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
        username = "jihoi",
        email = "jihoi.kang@gmail.com",
        password = "aa120000",
        passwordConfirm = "aa120000",
        onClick = { },
    )
}