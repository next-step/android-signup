package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.ValidationState
import nextstep.signup.ui.screen.signup.component.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val validationState = mutableStateOf<ValidationState>(ValidationState.Success)

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                username = "hyem",
                onUsernameChange = {},
                validationState = validationState.value
            )
        }
    }

    @Test
    fun 이름_값에_대한_검증_성공_값이_들어온다면_길이에_대한_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_username_length)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이름_값에_대한_검증_성공_값이_들어온다면_형식에_대한_에러메시지를_노출하지_않는다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_username_format)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이름_길이에_대한_검증_오류_값이_들어온다면_길이에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_username_length)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름_형식에_대한_검증_오류_값이_들어온다면_형식에_대한_에러메시지를_노출한다() {
        // when
        validationState.value = ValidationState.Error(R.string.error_username_format)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
