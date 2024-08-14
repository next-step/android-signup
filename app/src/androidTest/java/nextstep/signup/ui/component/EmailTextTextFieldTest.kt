package nextstep.signup.ui.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.signup.core.validation.EmailValidationResult
import nextstep.signup.core.validation.EmailValidator
import nextstep.signup.utils.assertDoesNotExist
import nextstep.signup.utils.assertExists
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val emailValidationResult by derivedStateOf { EmailValidator().validate(textFieldValue.value) }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = textFieldValue.value,
                onEmailChange = { textFieldValue.value = it },
                emailValidationResult = emailValidationResult
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""

        composeTestRule.assertDoesNotExist("이메일 형식이 올바르지 않습니다.")
    }

    @Test
    fun 이메일이_올바른_형식일_때_에러가_없다() {
        textFieldValue.value = "dlwlgns1240@gmail.com"

        composeTestRule.assertDoesNotExist("이메일 형식이 올바르지 않습니다.")
    }

    @Test
    fun 이메일은_올바른_형식이어야_한다() {
        textFieldValue.value = "invalid_email"

        composeTestRule.assertExists("이메일 형식이 올바르지 않습니다.")
    }
}
