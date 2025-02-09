package nextstep.signup.view

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val passwordConfirm = mutableStateOf("")

    private val password = "1234asdf"

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                value = passwordConfirm.value,
                password = password,
                onValueChange = {
                    passwordConfirm.value = it
                }
            )
        }
    }

    @Test
    fun 패스워드_확인_값은_패스워드와_일치해야_한다() {
        // when
        passwordConfirm.value = password

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_confirm_password_error))
            .assertDoesNotExist()
    }

    @Test
    fun 패스워드_확인_값이_패스워드와_일치하지_않으면_에러메시지가_노출된다() {
        // when
        passwordConfirm.value = "1234as"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_confirm_password_error))
            .assertExists()
    }

}