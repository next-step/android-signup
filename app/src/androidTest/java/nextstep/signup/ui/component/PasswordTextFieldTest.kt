package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.PasswordValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val validationResult = mutableStateOf(PasswordValidationResult.SUCCESS)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                validationResult = validationResult.value,
                onPasswordChange = { password.value = it }
            )
        }
    }

    @Test
    fun 유효성_검사가_정상이면_에러메시지가_노출되지_않는다() {
        // when
        password.value = "1q2w3e4r!"
        validationResult.value = PasswordValidationResult.SUCCESS

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 유효성_검사가_길이_오류이면_에러메시지가_노출된다() {
        // when
        password.value = "1234"
        validationResult.value = PasswordValidationResult.INVALID_LENGTH

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 유효성_검사가_포맷_오류이면_에러메시지가_노출된다() {
        // when
        password.value = "1234567890"
        validationResult.value = PasswordValidationResult.INVALID_FORMAT

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_비어있으면_에러메시지가_노출되지_않는다() {
        // when
        password.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }


    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
