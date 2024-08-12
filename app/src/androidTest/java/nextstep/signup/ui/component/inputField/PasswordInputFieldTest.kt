package nextstep.signup.ui.component.inputField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val context = InstrumentationRegistry.getInstrumentation().targetContext
    private val password = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PasswordInputField(
                password = password.value,
                onValueChange = { password.value = it },
                onValidationSuccess = {}
            )
        }
    }

    @Test
    fun 비밀번호_길이는_8자_이상_16자_이하여야_한다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_password_input_validation_length_message)
        val valueSource = listOf("password", "passwordpassword")

        valueSource.forEach {
            // when
            password.value = it

            // then
            composeTestRule
                .onNodeWithText(errorMessage)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 비밀번호_길이가_8자_이상_16자_이하가_아니면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_password_input_validation_length_message)
        val valueSource = listOf("passwor", "passwordpasswordp")

        valueSource.forEach {
            // when
            password.value = it

            // then
            composeTestRule
                .onNodeWithText(errorMessage)
                .assertExists()
        }
    }

    @Test
    fun 비밀번호가_영문_또는_숫자_포함하지_않으면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_password_input_validation_message)

        // when
        password.value = "password"

        // then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지_않으면_에러메시지가_노출된다() {
        // given
        val errorMessage = context.getString(R.string.sign_up_password_not_equals_password_confirm_message)

        // when
        password.value = "password1234"
        composeTestRule.onNodeWithTag("passwordConfirm")
            .performTextInput("password123")

        // then
        composeTestRule
            .onNodeWithText(errorMessage)
            .assertExists()
    }
}
