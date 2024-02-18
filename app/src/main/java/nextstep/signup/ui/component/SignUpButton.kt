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

@Composable
fun SignUpButton(
    modifier: Modifier = Modifier,
    isEnabled: Boolean
) {
    Button(
        modifier = modifier
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

@Preview
@Composable
private fun Preview() {
    SignUpButton(
        modifier = Modifier,
        isEnabled = true
    )
}

enum class SignUpEnable {
    ENABLED,
    DISABLED;

    val isEnabled: Boolean
        get() = this == ENABLED

    companion object {
        fun checkBy(
            userName: String,
            email: String,
            password: String,
            confirm: String
        ): SignUpEnable {
            return when {
                userName.isEmpty() || email.isEmpty() || password.isEmpty() -> {
                    DISABLED
                }

                UserNameError.checkBy(userName = userName).isPass
                        && EmailError.checkBy(email = email).isPass
                        && PasswordError.checkBy(password = password).isPass
                        && ConfirmError.checkBy(src = password, dst = confirm).isPass -> {
                    ENABLED
                }

                else -> {
                    DISABLED
                }
            }
        }
    }
}
