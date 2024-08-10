package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.ui.theme.screen.TextFieldView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val passwordData = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            Column {
                TextFieldView("Password", keyboardType = KeyboardType.Password, inputState = passwordData)
                TextFieldView("Password Confirm", keyboardType = KeyboardType.Password, inputState = passwordConfirm, anotherInputState = passwordData)
            }
        }
    }

    @Test
    fun 비밀번호는_8_16_자여야_한다() {
        // when
        passwordData.value = "1q2w3e4r"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자보다_작으면_에러노출() {
        // when
        passwordData.value = "1q2w3e4"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자보다_크면_에러노출() {
        // when
        passwordData.value = "1q2w3e4r5t6y7u8i9"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야한다() {
        // when
        passwordData.value = "1q2w3e4r"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REGEX_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_영문이_없으면_에러메시지_노출() {
        // when
        passwordData.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_숫자가_없으면_에러메시지_노출() {
        // when
        passwordData.value = "abcdefghi"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호와_확인비밀번호는_일치해야_한다() {
        // when
        passwordData.value = "1q2w3e4r"
        passwordConfirm.value = "1q2w3e4r"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_COMPARE_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_확인비밀번호는_일치하지않으면_에러메시지_노출() {
        // when
        passwordData.value = "1q2w3e4r"
        passwordConfirm.value = "1q2w3e4"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_COMPARE_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_REGEX_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_COMPARE_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
