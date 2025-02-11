package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.screen.PasswordTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                onPasswordChange = {}
            )
        }
    }

    @Test
    fun 비밀번호는_8자에서_16자여야_한다() {
        // when
        password.value = "compose1234"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자_미만이면_에러메시지가_노출된다() {
        // when
        password.value = "compo12"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자_초과이면_에러메시지가_노출된다() {
        // when
        password.value = "compose1234compos"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호에_영문을_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "12341234"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호에_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        password.value = "doolydooly"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}
