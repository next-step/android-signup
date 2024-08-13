package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.core.validation.PasswordValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val passwordValidationResult = mutableStateOf(PasswordValidationResult.VALID)

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = textFieldValue.value,
                onPasswordChange = { textFieldValue.value = it },
                passwordValidationResult = passwordValidationResult.value,
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""
        passwordValidationResult.value = PasswordValidationResult.VALID

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자이내이며_영문과_숫자를_포함할_때_에러가_없다() {
        textFieldValue.value = "Password123"
        passwordValidationResult.value = PasswordValidationResult.VALID

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertDoesNotExist()
    }


    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        textFieldValue.value = "short"
        passwordValidationResult.value = PasswordValidationResult.INVALID_LENGTH

        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        textFieldValue.value = "Password"
        passwordValidationResult.value = PasswordValidationResult.INVALID_COMPLEXITY

        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }
}
