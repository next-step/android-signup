package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.component.PasswordConfirmTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val validationState = mutableStateOf<ValidationState>(ValidationState.Success)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                passwordConfirm = "hyem1234",
                onPasswordConfirmChange = {},
                validationState = validationState.value
            )
        }
    }


    @Test
    fun 비밀번호_확인_값_검증_성공_값이_들어온다면_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Success

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_NOT_EQUAL_ERROR)
            .assertDoesNotExist()

    }

    @Test
    fun 비밀번호와_비밀번호_확인_값이_같지_않다는_검증_오류_값이_들어온다면_이에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_password_confirm_not_equal)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_NOT_EQUAL_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_CONFIRM_NOT_EQUAL_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
