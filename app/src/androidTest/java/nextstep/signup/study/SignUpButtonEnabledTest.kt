package nextstep.signup.study

import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.SignUpUiState
import nextstep.signup.ui.component.SignUpButton
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonEnabledTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var signUpUiState by mutableStateOf(SignUpUiState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()
            val context = LocalContext.current

            SnackbarHost(snackbarHostState)

            SignUpButton(
                onClick = {
                    scope.launch {
                        snackbarHostState.showSnackbar(
                            message = context.getString(R.string.signupSuccessSnackBarContentDescription),
                            actionLabel = context.getString(R.string.signupSuccessSnackBarActionLabel),
                            duration = SnackbarDuration.Short
                        )
                    }
                },
                colors = ButtonDefaults.buttonColors(
                    contentColor = Color.White,
                    disabledContainerColor = Color.LightGray,
                    disabledContentColor = Color.Gray
                ),
                enabled = signUpUiState.isSignUpButtonEnabled
            )
        }
    }

    @Test
    fun 모든_필드_값이_비어있으면_버튼은_비활성화_상태이다() {
        // when
        signUpUiState = SignUpUiState()

        // then
        composeTestRule
            .onNodeWithText(SIGN_UP_BUTTON_TITLE)
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_한_개의_필드라도_유효성_검사_실피시_버튼은_비활성화_상태이다() {
        // when
        signUpUiState = SignUpUiState(username = "12134@")

        // then
        composeTestRule
            .onNodeWithText(SIGN_UP_BUTTON_TITLE)
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_필드가_비어있지_않고_유효성_검사_성공시_버튼은_활성화_상태이다() {
        // when
        signUpUiState = SignUpUiState(username = "asd", email = "123@gmail.com", password = "asdf1234", passwordConfirm = "asdf1234")

        // then
        composeTestRule
            .onNodeWithText(SIGN_UP_BUTTON_TITLE)
            .assertIsEnabled()
    }

    @Test
    fun 활성화된_회원가입_버튼을_클릭시_스낵바가_노출된다() {
        signUpUiState = SignUpUiState(username = "asd", email = "123@gmail.com", password = "asdf1234", passwordConfirm = "asdf1234")

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