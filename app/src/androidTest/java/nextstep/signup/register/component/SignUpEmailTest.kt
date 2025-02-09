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

class SignUpEmailTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private var email by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFiled.Email(
                email = email,
                onChangedEmail = { email = it })
        }
    }
    @Test
    fun should_not_show_error_when_empty_email() {
        //given
        email = ""

        //then
        composeTestRule
            .onNodeWithText(ERROR_EMAIL)
            .assertDoesNotExist()
    }


    @Test
    fun should_show_error_when_wrong_format_email() {
        //given
        email = "abc@a"

        //then
        composeTestRule
            .onNodeWithText(ERROR_EMAIL)
            .assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_correct_format_email() {
        //given
        email = "abc@abc.com"

        //then
        composeTestRule
            .onNodeWithText(ERROR_EMAIL)
            .assertDoesNotExist()
    }

    companion object {
        private const val ERROR_EMAIL = "이메일 형식이 올바르지 않습니다."
    }
}