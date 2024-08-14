package nextstep.signup.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.ui.signup.PasswordConfirmField
import org.junit.Before
import org.junit.Rule
import org.junit.Test


class PasswordConfirmFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    private var password by mutableStateOf("")
    private var passwordConfirm by mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmField(
                password = password,
                passwordConfirm = passwordConfirm,
                onPasswordConfirmChange = { passwordConfirm = it },
                onPasswordConfirmValidationSuccess = { }
            )
        }
    }

    @Test
    fun 비밀번호와_비밀번호_확인_글자가_같아야_한다() {
        // when
        password = "abcd1234"
        passwordConfirm = "abcd1234"

        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_password_confirm))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인_글자가_같지_않으면_에러_노출() {
        // when
        password = "abcd5678"
        passwordConfirm = "abcd1234"

        composeTestRule
            .onNodeWithText(context.getString(R.string.error_invalid_password_confirm))
            .assertExists()
    }
}
