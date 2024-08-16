package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.ui.signup.PasswordField
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PasswordFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var password by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordField(
                password = password,
                onPasswordChange = { password = it },
                onPasswordValidationSuccess = { }
            )
        }
    }

    @Test
    fun 비밀번호는_8자_이상_16자_이하여야_합니다() {
        // when
        password = "12345678"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_password_length))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자_이상_16자가_아니면_에러_노출() {
        // when
        password = "1234"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_password_length))
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        // when
        password = "abcd1234"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_password))
            .assertDoesNotExist()
    }
}
