package nextstep.signup.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import nextstep.signup.view.ui.theme.Dimens.EndPadding
import nextstep.signup.view.ui.theme.Dimens.LargePadding
import nextstep.signup.view.ui.theme.Dimens.StartPadding
import nextstep.signup.view.ui.theme.Dimens.TopPadding
import nextstep.signup.view.ui.theme.SignupTheme

class SignUpScreen {
    @Composable
    fun Screen() {
        var password by remember { mutableStateOf("") }
        var passwordConfirm by remember { mutableStateOf("") }
        var isMatchPassword by remember { mutableStateOf(true) }

        SignupTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(
                            top = TopPadding,
                            start = StartPadding,
                            end = EndPadding
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    SignUpTitle()
                    Spacer(modifier = Modifier.height(LargePadding))
                    UsernameTextField()
                    Spacer(modifier = Modifier.height(LargePadding))
                    EmailTextField()
                    Spacer(modifier = Modifier.height(LargePadding))
                    PasswordTextField(
                        onTextChange = {
                            password = it
                            isMatchPassword = password == passwordConfirm
                        }
                    )
                    Spacer(modifier = Modifier.height(LargePadding))
                    PasswordConfirmTextField(
                        onTextChange = {
                            passwordConfirm = it
                            isMatchPassword = password == passwordConfirm
                        },
                        isMatchPassword,
                    )
                    Spacer(modifier = Modifier.height(LargePadding))
                    SignUpButton()
                }
            }
        }
    }
}