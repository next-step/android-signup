package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val usernameValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val emailValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val passwordValidationState = mutableStateOf<ValidationState>(ValidationState.Success)
    private val passwordConfirmValidationState =
        mutableStateOf<ValidationState>(ValidationState.Success)
    private val buttonEnabled = mutableStateOf(true)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupScreen(
                username = "",
                email = "",
                password = "",
                passwordConfirm = "",
                onUsernameChange = {},
                onEmailChange = {},
                onPasswordChange = {},
                onPasswordConfirmChange = {},
                usernameValidationState = usernameValidationState.value,
                emailValidationState = emailValidationState.value,
                passwordValidationState = passwordValidationState.value,
                passwordConfirmValidationState = passwordConfirmValidationState.value,
                buttonEnabled = buttonEnabled.value,
                showSnackbar = {}
            )
        }
    }

    @Test
    fun 버튼_상태_값이_true면_누를_수_있는_상태이다() {
        // when
        buttonEnabled.value = true

        // then
        composeTestRule
            .onNodeWithTag("SignupButton")
            .assertIsEnabled()
    }

    @Test
    fun 버튼_상태_값이_false면__누를_수_없는_상태이다() {
        // when
        buttonEnabled.value = false

        // then
        composeTestRule
            .onNodeWithTag("SignupButton")
            .assertIsNotEnabled()
    }
}
