package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.screen.PasswordConfirmTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val password = "1234qwer"
    private val confirmPassword = mutableStateOf<String>("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PasswordConfirmTextField(
                password = password,
                confirmPassword = confirmPassword.value,
                onPasswordChange = { confirmPassword.value = it },
                modifier = Modifier.testTag(PASSWORD_CONFIRM_TEST_TAG)
            )
        }
    }

    @Test
    fun 비밀번호가_일치해야_한다() {
        // when
        val confirmPassword = "1234qwer"

        // then
        composeTestRule.onNodeWithTag(PASSWORD_CONFIRM_TEST_TAG)
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag(PASSWORD_CONFIRM_TEST_TAG)
            .assert(!hasText(PASSWORD_CONFIRM_ERROR))
    }

    @Test
    fun 비밀번호가_일치하지_않는_경우_오류메시지가_출력된다() {
        // when
        val confirmPassword = "1234abcde"

        // then
        composeTestRule.onNodeWithTag(PASSWORD_CONFIRM_TEST_TAG)
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag(PASSWORD_CONFIRM_TEST_TAG)
            .assert(hasText(PASSWORD_CONFIRM_ERROR))
    }

    companion object {
        const val PASSWORD_CONFIRM_TEST_TAG = "ConfirmPassword"
        const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."
    }
}