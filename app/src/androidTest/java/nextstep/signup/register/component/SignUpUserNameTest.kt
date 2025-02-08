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

class SignUpUserNameTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
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
    fun should_show_error_when_not_username_length_2_5() {
        //given
        username = "a"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_username_length)
            ).assertIsDisplayed()
    }

    @Test
    fun should_show_error_when_username_contain_digit_or_symbol() {
        //given
        username = "abc@"

        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_username)
            ).assertIsDisplayed()
    }

    @Test
    fun should_not_show_error_when_correct_username() {
        //given
        username = "abc"


        //then
        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_username_length)
            ).assertDoesNotExist()

        composeTestRule
            .onNodeWithText(
                context.getString(R.string.sign_up_error_username)
            ).assertDoesNotExist()
    }
}