package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.PasswordConfirmValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val validationResult = mutableStateOf(PasswordConfirmValidationResult.SUCCESS)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                password = password.value,
                validationResult = validationResult.value,
                onPasswordChange = { password.value = it }
            )
        }
    }

    @Test
    fun 유효성_검사가_정상이면_오류메시지가_노출되지_않는다() {
        // when
        password.value = "1q2w3e4r!"
        validationResult.value = PasswordConfirmValidationResult.SUCCESS

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 유효성_검사가_비정상이면_에러메시지가_노출된다() {
        // when
        password.value = "1234"
        validationResult.value = PasswordConfirmValidationResult.INVALID

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        password.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DIFF_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_DIFF_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
