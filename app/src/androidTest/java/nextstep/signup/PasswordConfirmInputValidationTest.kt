package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.textfield.PasswordConfirmTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.util.UUID

class PasswordConfirmInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val passwordConfirm = mutableStateOf("")
    private val isPasswordMatched = mutableStateOf(true)

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(value = passwordConfirm.value, isPasswordMatched = isPasswordMatched.value, onValueChange = {})
        }
    }

    @Test
    fun 비밀번호_확인이_빈문자열이_아니고_비밀번호와_일치하면_에러메세지가_노출되지_않는다() {
        // when
        passwordConfirm.value = UUID.randomUUID().toString()
        isPasswordMatched.value = true

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_MATCHED_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인이_빈문자열이_아니고_비밀번호와_일치하지_않으면_에러메세지가_노출된다() {
        // when
        passwordConfirm.value = "qwer123"
        isPasswordMatched.value = false

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_MATCHED_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_확인이_빈문자열이면_에러메세지가_노출되지_않는다() {
        // when
        passwordConfirm.value = ""

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_MATCHED_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val PASSWORD_NOT_MATCHED_ERROR = "비밀번호가 일치하지 않습니다."
    }
}