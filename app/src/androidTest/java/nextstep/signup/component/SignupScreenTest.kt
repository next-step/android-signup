package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized

/**
 * @author : interworks_aos
 * @CreatedDate : 2025. 2. 10. 오후 5:05
 * @PackageName : nextstep.signup.component
 * @ClassName: SignupScreenTest
 * @Description:
 */
@RunWith(Parameterized::class)
class SignupScreenTest(
    private val userName: String,
    private val email: String,
    private val password: String,
    private val passwordConfirm: String,
) {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val snackBarMessage = "회원가입 완료!"

    @Before
    fun setup() {
        composeTestRule.setContent {
            val scope = rememberCoroutineScope()
            val snackBarHostState = remember { SnackbarHostState() }

            Scaffold(
                modifier = Modifier.fillMaxSize(),
                snackbarHost = { SnackbarHost(snackBarHostState) }
            ) { padding ->
                SignupScreen(
                    modifier = Modifier.padding(padding),
                    onClickSignUp = {
                        scope.launch {
                            snackBarHostState.showSnackbar(snackBarMessage)
                        }
                    }
                )
            }
        }
    }

    @Test
    fun `모든입력이_유효하면_버튼이_활성화된다`() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(userName)

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(email)

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(password)

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText("Sign Up")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText(snackBarMessage)
            .assertIsDisplayed()
    }


    companion object {
        @JvmStatic
        @Parameterized.Parameters
        fun params(): Collection<Array<Any>> {
            return listOf(
                arrayOf("컴포즈", "android@gmail.com", "1q2w3e4r", "1q2w3e4r"),
            )
        }
    }
}
