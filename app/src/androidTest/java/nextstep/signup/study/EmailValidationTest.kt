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

class EmailValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private lateinit var inputValidation: InputValidation.EmailValidation
    private lateinit var invalidMsg: String

    @Before
    fun setup() {
        composeTestRule.setContent {
            invalidMsg = stringResource(R.string.email_invalid_msg)
            inputValidation = InputValidation.EmailValidation(
                invalidMsg
            )

            SignUpTextFieldComponent(
                labelText = stringResource(R.string.input_email),
                { inputValidation.checkValidation(email.value) }
            )
        }
    }

    @Test
    fun 이메일에_맞는형식은_에러메세지가_노출되지_않는다() {
        // when
        email.value = "1234qwer@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다1() {
        // when
        email.value = "1234qwer@gmai"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다2() {
        // when
        email.value = "1234qwer.com"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다3() {
        // when
        email.value = "1234qwer"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }
}
