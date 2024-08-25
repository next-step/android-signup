package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.validation.InputValidation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")
    private val inputValidation = InputValidation.EmailValidation(
        EMAIL_VALID_ERROR
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFieldComponent(
                labelText = "password",
                {  inputValidation.checkValidation(email.value) }
            )
        }
    }

    @Test
    fun 이메일에_맞는형식은_에러메세지가_노출되지_않는다() {
        // when
        email.value = "1234qwer@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_VALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다1() {
        // when
        email.value = "1234qwer@gmai"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_VALID_ERROR)
            .assertExists()

    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다2() {
        // when
        email.value = "1234qwer.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_VALID_ERROR)
            .assertExists()

    }

    @Test
    fun 이메일에_맞지않는_형식은_에러메세지가_노출된다3() {
        // when
        email.value = "1234qwer"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_VALID_ERROR)
            .assertExists()

    }

    companion object {
        private const val EMAIL_VALID_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
