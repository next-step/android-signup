package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.component.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val validationState = mutableStateOf<ValidationState>(ValidationState.Success)

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = "hyemdooly@gmail.com",
                onEmailChange = {},
                validationState = validationState.value
            )
        }
    }

    @Test
    fun 이메일_형식에_대한_검증_성공_값이_들어온다면_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Success

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()

    }

    @Test
    fun 이메일_형식에_대한_검증_오류_값이_들어온다면_형식에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_email_format)

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
