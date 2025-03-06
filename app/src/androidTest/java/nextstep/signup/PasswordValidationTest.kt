package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.PasswordVisualTransformation
import nextstep.signup.ui.component.PasswordTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                value = password.value,
                onValueChange = { newValue ->
                    password.value = newValue
                },
                errorMessage = InputValidation.validatePassword(password.value)?.let {
                    stringResource(it)
                },
                visualTransformation = PasswordVisualTransformation()
            )
        }
    }

    @Test
    fun 패스워드는_8에서_16자여야_한다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("password12#")

        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 패드웨드가_8자_미만이면_에러메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("1234")

        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 패드웨드가_16자_초과이면_에러메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("123456789012345678")

        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 패스워드는_영문과_숫자를_포함해야_한다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("password12")

        composeTestRule
            .onNodeWithText(PASSWORD_CONTAIN_CHARACTERS_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 패스워드가_영문을_포함하지_않으면_에러메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("!@#$%^&12")

        composeTestRule
            .onNodeWithText(PASSWORD_CONTAIN_CHARACTERS_ERROR)
            .assertExists()
    }

    @Test
    fun 패스워드가_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        composeTestRule
            .onNodeWithText("password")
            .performTextInput("passwordpasd")

        composeTestRule
            .onNodeWithText(PASSWORD_CONTAIN_CHARACTERS_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_CONTAIN_CHARACTERS_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}