package nextstep.signup.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import nextstep.signup.ui.component.NSTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            NSTextField(
                value = password.value,
                onValueChange = {},
                labelValue = stringResource(id = R.string.password),
                supportingTextValue = getPasswordSupportingText(password.value),
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        password.value = "aa120000"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_16자초과하면_에러메시지가_노출된다() {
        // when
        password.value = "aa120000aa1200001"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_8자미만이면_에러메시지가_노출된다() {
        // when
        password.value = "aa12000"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문으로만_되어있으면_에러메시지가_노출된다() {
        // when
        password.value = "aabbccdd"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_숫자로만_되어있으면_에러메시지가_노출된다() {
        // when
        password.value = "11223344"

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_REGEX_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_REGEX_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }

}