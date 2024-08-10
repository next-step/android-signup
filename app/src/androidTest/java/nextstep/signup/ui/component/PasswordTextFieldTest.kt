package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordValidation = PasswordValidation()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                value = password.value,
                onValueChange = { password.value = it },
                validationResult = passwordValidation.isValid(password.value),
            )
        }
    }

    @Test
    fun 비밀번호가_8자리_이상_16자리_이하이며_영문과_숫자가_반드시_포함되어_있으면_에러메시지가_노출되지_않는다() {
        // given
        password.value = "ab123456789"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText('\u2022'.toString().repeat(password.value.length))
            .assertExists()
    }

    @Test
    fun 비밀번호가_8자리_미만이면_에러메시지가_노출된다() {
        // given
        password.value = "12345"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자리_초과이면_에러메시지가_노출된다() {
        // given
        password.value = "12345678901234567"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_8자리_이상_16자리_이하이지만_영문과_숫자가_반드시_포함되어_있지_않으면_에러메시지가_노출된다() {
        // given
        password.value = "123456789"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자가_반드시_포함되어_있지만_8자리_미만이면_에러메시지가_노출된다() {
        // given
        password.value = "ab12345"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자가_반드시_포함되어_있지만_16자리_초과이면_에러메시지가_노출된다() {
        // given
        password.value = "ab123456789abcdefg"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
