package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.signup.UserNameValidationState
import nextstep.signup.ui.signup.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var username by mutableStateOf("")
    private var usernameValidation by mutableStateOf(UserNameValidationState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                username = username,
                isUsernameValid = usernameValidation.isValidUsername,
                isUsernameLengthValid = usernameValidation.isInLength,
                isUsernameHasNumberOrSpecialCharacter = usernameValidation.hasNumber && usernameValidation.hasSpecialCharacter,
                onUsernameChange = { },
            )
        }
    }

    @Test
    fun 사용자_이름이_길이_내에_있다면_오류가_노출되지_않는다() {
        // when
        username = "김컴포즈"
        usernameValidation = UserNameValidationState(
            isInLength = true,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_허용된_길이가_아니면_에러메시지가_노출된다() {
        // when
        username = "김컴포즈입니다"
        usernameValidation = UserNameValidationState(
            isInLength = false,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_없으면_에러메시지가_노출되지_않는다() {
        // when
        username = "컴포즈"
        usernameValidation = UserNameValidationState(
            hasNumber = false,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_HAS_NUMBER_OR_SPECIAL_CHARACTER_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에_숫자가_있으면_에러메시지가_노출되지_않는다() {
        // when
        username = "컴포즈3"
        usernameValidation = UserNameValidationState(
            hasNumber = true,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_HAS_NUMBER_OR_SPECIAL_CHARACTER_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_있으면_에러메시지가_노출된다() {
        // when
        username = "컴포즈!"
        usernameValidation = UserNameValidationState(
            hasSpecialCharacter = false,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_HAS_NUMBER_OR_SPECIAL_CHARACTER_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_없으면_에러메시지가_노출되지_않는다() {
        // when
        username = "컴포즈!"
        usernameValidation = UserNameValidationState(
            hasSpecialCharacter = false,
        )

        // then
        composeTestRule
            .onNodeWithText(USERNAME_HAS_NUMBER_OR_SPECIAL_CHARACTER_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_HAS_NUMBER_OR_SPECIAL_CHARACTER_ERROR =
            "이름에는 숫자나 기호가 포함될 수 없습니다."
    }

}
