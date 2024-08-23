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

class UserNameTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val userNameValidationResult =
        derivedStateOf { Validator.userNameValidate(username.value) }

    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameTextField(
                text = username.value,
                validationResult = userNameValidationResult.value,
                onValueChange = {
                    username.value = it
                }
            )
        }
    }

    @Test
    fun `사용자_이름은_2에서_5자여야_한다`() {
        username.value = "김컴포즈"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun `사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다`() {
        username.value = "김컴포즈입니다"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_length_error))
            .assertExists()
    }

    @Test
    fun `사용자_이름에_숫자가_있으면_에러메시지가_노출된다`() {
        username.value = "김컴포즈1"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_regex_error))
            .assertExists()
    }

    @Test
    fun `사용자_이름에_기호가_있으면_에러메시지가_노출된다`() {
        username.value = "김컴포즈@"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up_user_name_regex_error))
            .assertExists()
    }
}