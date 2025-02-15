package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.component.PasswordTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val validationState = mutableStateOf<ValidationState>(ValidationState.Success)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = "hyem1234",
                onPasswordChange = {},
                validationState = validationState.value
            )
        }
    }

    @Test
    fun 비밀번호_값에_대한_검증_성공_값이_들어온다면_길이_관련_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Success

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_값에_대한_검증_성공_값이_들어온다면_포맷_관련_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Success

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_길이에_대한_검증_오류_값이_들어온다면_길이에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_password_length)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_형식에_대한_검증_오류_값이_들어온다면_형식에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_password_format)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
