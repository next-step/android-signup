package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
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
import nextstep.signup.ui.theme.Blue50

@Preview
@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    userName: String = "",
    email: String = "",
    password: String = "",
    passwordConfirm: String = ""
) {
    val isEnabled by remember(userName, email, password, passwordConfirm) {
        val isEmpty = userName.isEmpty() || email.isEmpty() || password.isEmpty()
        mutableStateOf(
            isEmpty.not() &&
                    UserNameError.checkBy(userName = userName) == UserNameError.NONE
                    && EmailError.checkBy(email = email) == EmailError.NONE
                    && PasswordError.checkBy(password = password) == PasswordError.NONE
                    && ConfirmError.checkBy(
                src = password,
                dst = passwordConfirm
            ) == ConfirmError.NONE
        )
    }
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .height(height = 50.dp)
            .testTag(tag = "signUpButton"),
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50
        ),
        enabled = isEnabled,
        onClick = {},
    ) {
        Text(text = stringResource(R.string.sign_up))
    }
}
