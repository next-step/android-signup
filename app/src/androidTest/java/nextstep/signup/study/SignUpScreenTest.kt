package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.component.InputErrorText
import nextstep.signup.ui.component.SignUpTextField
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleTest() {
        val text = "Welcome to Compose \uD83D\uDE80"

        composeTestRule.setContent {
            TitleCompose(text = text)
        }

        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }

    @Test
    fun userNameTextFieldTest() {
        val label = "UserName"

        composeTestRule.setContent {
            var userName by rememberSaveable { mutableStateOf("") }
            SignUpTextField(text = userName, label = label, onTextValueChange = { userName = it })
        }

        composeTestRule
            .onNodeWithText(label)
            .assertExists()
    }

    @Test
    fun emailTextFieldTest() {
        val label = "Email"

        composeTestRule.setContent {
            EmailTextField(label = label)
        }

        composeTestRule
            .onNodeWithText(label)
            .assertExists()
    }

    @Test
    fun passwordTextFieldTest() {
        val label = "Password"

        composeTestRule.setContent {
            PasswordTextField(label = label)
        }

        composeTestRule
            .onNodeWithText(label)
            .assertExists()
    }

    @Test
    fun 유저_이름의_길이가_2이상_5이하일_경우_에러_메시지가_노출되지_않습니다() {
       // when : 유저 이름의 길이가 3입니다.
        val userName = "OYJ"
        composeTestRule.setContent {
            InputErrorText(userName, userNameErrorMsg, userNameRegex)
        }

        // then : 에러 메시지가 노출되지 않습니다.
        composeTestRule
            .onNodeWithText(userNameErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 유저_이름의_길이가_2이상_5이하가_아닐_경우_에러_메시지가_노출됩니다() {
        // when : 유저 이름의 길이가 6입니다.
        val userName = "김오박이컴포즈"
        composeTestRule.setContent {
            InputErrorText(userName, userNameErrorMsg, userNameRegex)
        }

        // then : 에러 메시지가 노출됩니다.
        composeTestRule
            .onNodeWithText(userNameErrorMsg)
            .assertExists()
    }

    @Test
    fun 이메일_구성일_경우_에러_메시지가_노출되지_않습니다() {
        // when : 이메일 형식이 올바른 경우
        val email = "oyj7677@gmail.com"

        composeTestRule.setContent {
            InputErrorText(email, emailErrorMsg, emailRegex)
        }

        // then : 에러 메시지가 노출되지 않습니다.
        composeTestRule
            .onNodeWithText(emailErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_구성이_아닐경우_에러_메시지가_노출됩니다() {
        // when : 이메일 형식이 올바르지 않은 경우
        val email = "oyj7677@gmail"

        composeTestRule.setContent {
            InputErrorText(email, emailErrorMsg, emailRegex)
        }

        // then : 에러 메시지가 노출됩니다.
        composeTestRule
            .onNodeWithText(emailErrorMsg)
            .assertExists()
    }

    @Test
    fun 비밀번호_구성이_올바르다면_에러_메시지를_노출하지_않습ㄴ디ㅏ() {
        // when : 비밀번호가 올바른 경우
        // 길이는 8~16
        // 영문과 숫자 포함
        val password = "123okj!@#"

        composeTestRule.setContent {
            InputErrorText(text = password, errMsg = passwordErrorMsg, regex = passwordRegex)
        }

        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_구성이_올바르지_않다면_에러_메시지를_노출합니다() {
        // when : 비밀번호가 올바르지 않은 경우
        // 영문이 조합되지 않았다.
        val password = "123123123"

        composeTestRule.setContent {
            InputErrorText(text = password, errMsg = passwordErrorMsg, regex = passwordRegex)
        }

        // then : 에러 메시지가 노출됩니다.
        composeTestRule
            .onNodeWithText(passwordErrorMsg)
            .assertExists()
    }


    companion object {
        private const val userNameRegex = "^[a-zA-Z가-힣]{2,6}$"
        private const val emailRegex = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$"
        private const val passwordRegex = "^(?=.*[a-zA-Z])(?=.*[0-9]).{8,16}$"

        private const val userNameErrorMsg = "이름은 2~5자여야 합니다.\n이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val emailErrorMsg = "이메일 형식이 올바르지 않습니다."
        private const val passwordErrorMsg = "비밀번호는 8~16자, 영문, 숫자 조합이어야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val passwordConfirmErrorMsg = "비밀번호가 일치하지 않습니다."

    }
}

@Composable
fun TitleCompose(text: String) {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        text = text,
        fontSize = 26.sp,
        color = Color.Black,
        style = TextStyle(
            fontWeight = FontWeight.Bold
        )
    )
}

@Composable
fun UserNameTextField(label: String) {
    var userName by rememberSaveable { mutableStateOf("") }
    SignUpTextField(text = userName, label = label, onTextValueChange = { userName = it })
}

@Composable
fun EmailTextField(label: String) {
    var email by rememberSaveable { mutableStateOf("") }
    SignUpTextField(text = email, label = label, onTextValueChange = { email = it })
}


@Composable
fun PasswordTextField(label: String) {
    var password by rememberSaveable { mutableStateOf("") }
    SignUpTextField(text = password, label = label, onTextValueChange = { password = it })
}

@Composable
fun ButtonCompose() {
    Button(
        modifier = Modifier
            .padding(32.dp)
            .fillMaxWidth(),
        onClick = {}) {
        Text(
            text = "Sign Up",
            modifier = Modifier.padding(top = 16.dp, bottom = 16.dp, start = 24.dp, end = 24.dp)
        )
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun TitleComposePreview() {
    TitleCompose(text = "Welcome to Compose \uD83D\uDE80")
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
fun UserNameTextFieldComposePreview() {
    val label = "UserName"
    var userName by rememberSaveable { mutableStateOf("") }
    SignUpTextField(text = userName, label = label, onTextValueChange = { userName = it })
}

@Preview
@Composable
fun EmailTextFieldComposePreview() {
    EmailTextField("Email")
}

@Preview
@Composable
fun PasswordTextFieldComposePreview() {
    PasswordTextField("Password")
}

@Preview
@Composable
fun ButtonComposePreview() {
    ButtonCompose()
}