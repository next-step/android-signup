package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")
    private val passwordConfirmValidation = PasswordConfirmValidation()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                value = password.value,
                onValueChange = { password.value = it },
                validationResult =
                    passwordConfirmValidation.isValid(
                        PasswordConfirmValidation.PasswordConfirm(
                            password = password.value,
                            passwordConfirm = passwordConfirm.value,
                        ),
                    ),
            )
        }
    }

    @Test
    fun 비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // given
        password.value = "password"
        passwordConfirm.value = "password1"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_일치하면_에러메시지가_노출되지_않는다() {
        // given
        password.value = "password12"
        passwordConfirm.value = "password12"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText('\u2022'.toString().repeat(passwordConfirm.value.length))
            .assertExists()
    }

    @Test
    fun 비밀번호가_비어있으면_에러메시지가_노출되지_않는다() {
        // given
        password.value = ""
        passwordConfirm.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인이_비어있으면_에러메시지가_노출되지_않는다() {
        // given
        password.value = "password"
        passwordConfirm.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
