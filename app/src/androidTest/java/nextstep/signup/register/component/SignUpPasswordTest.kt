package nextstep.signup.register.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpPasswordTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var password by mutableStateOf("")


    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFiled.Password(
                password = password,
                onChangedPassword = { password = it })
        }
    }
    @Test
    fun should_not_show_error_when_empty_password() {
        //given
        password = ""

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_LENGTH)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(ERROR_PASSWORD)
            .assertDoesNotExist()
    }


    @Test
    fun should_show_error_when_not_password_length_8_16() {
        //given
        password = "abcdefg"

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_LENGTH)
            .assertIsDisplayed()
    }

    @Test
    fun should_show_error_when_not_contain_english_and_digit_password() {
        //given
        password = "12341234"

        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD)
            .assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_correct_password() {
        //given
        password = "qwer1234"


        //then
        composeTestRule
            .onNodeWithText(ERROR_PASSWORD_LENGTH)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(ERROR_PASSWORD)
            .assertDoesNotExist()
    }

    companion object {

        private const val ERROR_PASSWORD_LENGTH = "비밀번호는 8~16자여야 합니다."
        private const val ERROR_PASSWORD = "비밀번호는 영문과 숫자를 포함해야 합니다."

    }
}