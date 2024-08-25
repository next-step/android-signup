package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.validation.InputValidation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    private lateinit var passwordValidation: InputValidation.PasswordValidation
    private lateinit var invalidMsg: String
    private lateinit var invalidLengthMsg: String

    @Before
    fun setup() {
        composeTestRule.setContent {
            invalidMsg = stringResource(R.string.password_invalid_msg)
            invalidLengthMsg = stringResource(R.string.password_invalid_length_msg)
            passwordValidation = InputValidation.PasswordValidation(
                invalidMsg,
                invalidLengthMsg
            )

            SignUpTextFieldComponent(
                labelText = "password",
                {  passwordValidation.checkValidation(password.value) }
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        password.value = "1234qwer"

        // then
        composeTestRule
            .onNodeWithText(invalidLengthMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        password.value = "123qwe"

        // then
        composeTestRule
            .onNodeWithText(invalidLengthMsg)
            .assertExists()

    }

    @Test
    fun 비밀번호에_영문이_없으면_에러메시지가_노출된다() {
        // when
        password.value = "12345678"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }

    @Test
    fun 비밀번호에_숫자가없으면_에러메시지가_노출된다() {
        // when
        password.value = "qwertyuiop"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }
}
