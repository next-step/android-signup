package nextstep.signup.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.util.validation.Validator
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")
    private val passwordConfirmValidationResult =
        derivedStateOf { Validator.passwordConfirmValidate(password.value, passwordConfirm.value) }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                text = passwordConfirm.value,
                validationResult = passwordConfirmValidationResult.value,
                onValueChange = {
                    passwordConfirm.value = it
                }
            )
        }
    }

    @Test
    fun `비밀번호_확인이_비밀번호와_같으면_에러를_노출하지않는다`() {
        password.value = "1q2w3e4r"
        passwordConfirm.value = "1q2w3e4r"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_confirm_not_equal))
            .assertDoesNotExist()
    }

    @Test
    fun `비밀번호_확인이_비밀번호와_다르면_에러를_노출한다`() {
        password.value = "1q2w3e4r"
        passwordConfirm.value = "1q2w3e4rr"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_password_confirm_not_equal))
            .assertExists()
    }

}