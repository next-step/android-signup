package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.SignupTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val input = mutableStateOf("")
    private val errorMessage = mutableStateOf<String?>(null)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignupTextField (
                value = input.value,
                onValueChange = { input.value = it },
                label = "Label",
                errorMessage = errorMessage.value
            )
        }
    }

    @Test
    fun 에러메시지가_존재하면_에러메시지가_노출된다() { // abc@abc.de
        // when
        errorMessage.value = MESSAGE_ERROR

        // then
        composeTestRule
            .onNodeWithText(MESSAGE_ERROR)
            .assertExists()
    }

    @Test
    fun 에러메시지가_존재하지_않으면_에러메시지가_노출되지_않는다() {
        // when
        errorMessage.value = null

        // then
        composeTestRule
            .onNodeWithText(MESSAGE_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val MESSAGE_ERROR = "유효하지 않은 값입니다."
    }
}
