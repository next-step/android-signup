package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
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

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(email = "invalidemail")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_짧으면_에러메시지가_노출된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(password = "short")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_확인이_비밀번호와_일치하지_않으면_에러메시지가_노출된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(password = "Password123!", passwordConfirm = "Password124!")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertExists()
    }

    @Test
    fun 모든_필드가_올바르게_입력되면_회원가입_버튼이_활성화된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(
            username = "김컴포즈",
            email = "test@example.com",
            password = "Password123!",
            passwordConfirm = "Password123!"
        )

        // then
        composeTestRule
            .onNodeWithText(SIGN_UP_BUTTON)
            .assertIsEnabled()
    }

    @Test
    fun 회원가입_버튼을_클릭하면_스낵바에_메시지가_노출된다() {
        // when
        signUpUserInfo = signUpUserInfo.copy(
            username = "김컴포즈",
            email = "test@example.com",
            password = "Password123!",
            passwordConfirm = "Password123!"
        )

        composeTestRule.onNodeWithText(SIGN_UP_BUTTON).performClick()

        // then
        composeTestRule
            .onNodeWithText(COMPLETE_SIGNUP_MESSAGE)
            .assertExists()
    }
    
    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
        private const val SIGN_UP_BUTTON = "Sign Up"
        private const val COMPLETE_SIGNUP_MESSAGE = "회원가입이 완료되었습니다."
    }
}