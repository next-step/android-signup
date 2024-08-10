package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.ui.theme.screen.TextFieldView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailnputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val inputData = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            TextFieldView("UserName", keyboardType = KeyboardType.Email, inputString = inputData)
        }
    }

    @Test
    fun 이메일_형식이_올바른_경우_성공처리된다() {
        // when
        inputData.value = "mioyo2@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_REX_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않은경우_에러메시지가_노출된다() {
        // when
        inputData.value = "mioyo2gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_REX_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_REX_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
