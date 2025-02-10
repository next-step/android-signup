package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.signup.InputValidator
import nextstep.signup.ui.signup.UserNameValidationState
import nextstep.signup.ui.signup.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var username by mutableStateOf("")
    private var usernameValidation by mutableStateOf(UserNameValidationState())
    private val inputValidator = InputValidator()

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                username = username,
                isUsernameValid = usernameValidation.isValidUsername,
                isUsernameLengthValid = usernameValidation.isInLength,
                isUsernameHasNumberOrSpecialCharacter = usernameValidation.hasNumber && usernameValidation.hasSpecialCharacter,
                onUsernameChange = { },
                onImeClick = { TODO() },
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username = "김컴포즈"
        usernameValidation = inputValidator.checkUserName(username)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username = "김컴포즈입니다"
        usernameValidation = inputValidator.checkUserName(username)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
    }

}
