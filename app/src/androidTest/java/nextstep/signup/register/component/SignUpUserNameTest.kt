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

class SignUpUserNameTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var username by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFiled.UserName(
                userName = username,
                onChangedName = { username = it })
        }
    }

    @Test
    fun should_not_show_error_when_empty_username() {
        //given
        username = ""

        //then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(ERROR_USERNAME)
            .assertDoesNotExist()
    }

    @Test
    fun should_show_error_when_not_username_length_2_5() {
        //given
        username = "abcdef"

        //then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertIsDisplayed()
    }

    @Test
    fun should_show_error_when_username_contain_digit_or_symbol() {
        //given
        username = "abc@"

        //then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME)
            .assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_correct_username() {
        //given
        username = "abc"


        //then
        composeTestRule
            .onNodeWithText(ERROR_USERNAME_LENGTH)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(ERROR_USERNAME)
            .assertDoesNotExist()
    }

    companion object {
        private const val ERROR_USERNAME_LENGTH = "이름은 2~5자여야 합니다."
        private const val ERROR_USERNAME = "이름에는 숫자나 기호가 포함될 수 없습니다."

    }
}