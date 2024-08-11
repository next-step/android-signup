package nextstep.signup.screen

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val context = InstrumentationRegistry.getInstrumentation().targetContext

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = textFieldValue.value,
                onPasswordChange = { textFieldValue.value = it }
            )
        }
    }

    @Test
    fun 비밀번호가_정상_길이_내에_있을_때_에러가_없다() {
        textFieldValue.value = "Valid1234"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함할_때_에러가_없다() {
        textFieldValue.value = "Password123"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_complexity_error))
            .assertDoesNotExist()
    }


    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        textFieldValue.value = "short1"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_length_error))
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        textFieldValue.value = "Password"

        composeTestRule
            .onNodeWithText(context.getString(R.string.signup_password_complexity_error))
            .assertExists()
    }
}