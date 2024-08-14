package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.model.SignUpUserInfo
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var signUpUserInfo by mutableStateOf(SignUpUserInfo())

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(
                signUpUserInfo = signUpUserInfo,
                onUsernameChange = { signUpUserInfo = signUpUserInfo.copy(username = it) },
                onEmailChange = { signUpUserInfo = signUpUserInfo.copy(email = it) },
                onPasswordChange = { signUpUserInfo = signUpUserInfo.copy(password = it) },
                onPasswordConfirmChange = {
                    signUpUserInfo = signUpUserInfo.copy(passwordConfirm = it)
                },
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(username = "김컴포즈")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(username =  "김컴포즈입니다")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    }
}