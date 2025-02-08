package nextstep.signup.register.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpPasswordTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val context = InstrumentationRegistry.getInstrumentation().targetContext
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
    fun should_show_error_when_not_password_length_8_16() {
        //given
        password = "a"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password_length)
            ).assertIsDisplayed()
    }

    @Test
    fun should_show_error_when_not_contain_english_or_digit_password() {
        //given
        password = "12341234"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password)
            ).assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_correct_password() {
        //given
        password = "qwer1234"


        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password_length)
            ).assertDoesNotExist()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_password)
            ).assertDoesNotExist()
    }
}