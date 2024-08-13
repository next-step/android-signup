package nextstep.signup.study

import androidx.compose.material3.ButtonDefaults
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.flow.MutableStateFlow
import nextstep.signup.SignUpUiState
import nextstep.signup.ui.component.SignUpButton
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonEnabledTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val signUpUiState = MutableStateFlow(SignUpUiState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Gray
                ),
                enabled = signUpUiState.value.isSignUpButtonEnabled
            )
        }
    }

    @Test
    fun 모든_필드_값이_비어있으면_버튼은_비활성화_상태이다() {
        // when
        signUpUiState.value = SignUpUiState()

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_한_개의_필드라도_유효성_검사_실피시_버튼은_비활성화_상태이다() {
        // when
        signUpUiState.value = SignUpUiState(username = "12134@")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_필드가_비어있지_않고_유효성_검사_성공시_버튼은_활성화_상태이다() {
        // when
        signUpUiState.value = SignUpUiState(username = "asd", email = "123@gmail.com", password = "asdf1234", passwordConfirm = "asdf1234")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 활성화된_회원가입_버튼을_클릭시_스낵바가_노출된다() {
        signUpUiState.value = SignUpUiState(username = "asd", email = "123@gmail.com", password = "asdf1234", passwordConfirm = "asdf1234")

        composeTestRule
            .onNodeWithText(SIGN_UP_BUTTON_TITLE)
            .performClick()

        composeTestRule
            .onNodeWithText(SUCCESS_SIGNUP_MESSAGE)
            .assertIsDisplayed()
    }

    companion object {
        private const val SIGN_UP_BUTTON_TITLE = "Sign Up"
        private const val SUCCESS_SIGNUP_MESSAGE = "회원가입 성공!"
    }

}