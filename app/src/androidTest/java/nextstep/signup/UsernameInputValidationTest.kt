package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.textfield.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(value = username.value, onValueChange = {})
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메세지가_노출된다() {
        // when
        username.value = "1234입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러메세지가_노출된다() {
        // when
        username.value = "!@#입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_CHARACTER)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        username.value = ""

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
