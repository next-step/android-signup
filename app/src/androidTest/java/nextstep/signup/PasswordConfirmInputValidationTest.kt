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
    private val password = UUID.randomUUID().toString()
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmTextField(value = passwordConfirm.value, password = password, onValueChange = {})
        }
    }

    @Test
    fun 비밀번호와_비밀번호_확인은_일치해야한다() {
        // when
        passwordConfirm.value = password

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_NOT_MATCHED_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않으면_에러메세지가_노출된다() {
        // when
        passwordConfirm.value = "qwer123"

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