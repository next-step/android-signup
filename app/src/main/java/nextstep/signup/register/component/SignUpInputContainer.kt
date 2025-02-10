package nextstep.signup.register.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import nextstep.signup.register.SignUpValidation
import nextstep.signup.register.component.SignUpTextFiled.EMPTY_STRING
import nextstep.signup.register.component.SignUpTextFiled.Email
import nextstep.signup.register.component.SignUpTextFiled.Password
import nextstep.signup.register.component.SignUpTextFiled.PasswordConfirm
import nextstep.signup.register.component.SignUpTextFiled.UserName


@Composable
fun SignUpInputContainer(
    modifier: Modifier = Modifier,
    onChangeValid: (Boolean) -> Unit = {}
){

    var inputUserName by remember { mutableStateOf(EMPTY_STRING) }
    var inputEmail by remember { mutableStateOf(EMPTY_STRING) }
    var inputPassword by remember { mutableStateOf(EMPTY_STRING) }
    var inputPasswordConfirm by remember { mutableStateOf(EMPTY_STRING) }

    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(36.dp)
    ) {
        UserName(
            userName = inputUserName,
            onChangedName = {
                inputUserName = it
                onChangeValid(
                    SignUpValidation.isAllValid(
                        userName = inputUserName,
                        email = inputEmail,
                        password = inputPassword,
                        passwordConfirm = inputPasswordConfirm
                    )
                )
            }
        )
        Email(
            email = inputEmail,
            onChangedEmail = {
                inputEmail = it
                onChangeValid(
                    SignUpValidation.isAllValid(
                        userName = inputUserName,
                        email = inputEmail,
                        password = inputPassword,
                        passwordConfirm = inputPasswordConfirm
                    )
                )
            }
        )
        Password(
            password = inputPassword,
            onChangedPassword = {
                inputPassword = it
                onChangeValid(
                    SignUpValidation.isAllValid(
                        userName = inputUserName,
                        email = inputEmail,
                        password = inputPassword,
                        passwordConfirm = inputPasswordConfirm
                    )
                )
            }
        )
        PasswordConfirm(
            passwordConfirm = inputPasswordConfirm,
            onChangedPasswordConfirm = {
                inputPasswordConfirm = it
                onChangeValid(
                    SignUpValidation.isAllValid(
                        userName = inputUserName,
                        email = inputEmail,
                        password = inputPassword,
                        passwordConfirm = inputPasswordConfirm
                    )
                )
            },
            isShowError = !SignUpValidation.isValidPasswordConfirm(
                password = inputPassword,
                passwordConfirm = inputPasswordConfirm
            ) && inputPasswordConfirm.isNotEmpty()
        )
    }


}