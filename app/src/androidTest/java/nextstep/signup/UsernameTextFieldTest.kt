package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.textfield.UsernameError
import nextstep.signup.textfield.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val usernameError = mutableStateOf(UsernameError.NONE)

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(value = username.value, error = usernameError.value, onValueChange = {})
        }
    }

    @Test
    fun 사용자_이름이_빈문자열이_아니고_오류_타입이_NONE이면_에러메세지가_노출되지_않는다() {
        // when
        username.value = "김컴포즈"
        usernameError.value = UsernameError.NONE

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_빈문자열이_아니고_LENGTH_ERROR면_길이_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"
        usernameError.value = UsernameError.LENGTH_ERROR

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_빈문자열이_아니고_INVALID_CHARACTER_ERROR면_포함될_수_없는문자_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"
        usernameError.value = UsernameError.INVALID_CHARACTER_ERROR

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        username.value = ""
        usernameError.value = UsernameError.LENGTH_ERROR

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertDoesNotExist()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_CHARACTER = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
