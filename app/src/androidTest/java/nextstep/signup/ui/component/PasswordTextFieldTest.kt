package nextstep.signup.ui.component

import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import nextstep.signup.core.validation.PasswordValidationResult
import nextstep.signup.core.validation.PasswordValidator
import nextstep.signup.utils.assertDoesNotExist
import nextstep.signup.utils.assertExists
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordTextFieldTest {

    private val textFieldValue = mutableStateOf("")
    private val passwordValidationResult by derivedStateOf { PasswordValidator().validate(textFieldValue.value) }

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordTextField(
                password = textFieldValue.value,
                onPasswordChange = { textFieldValue.value = it },
                passwordValidationResult = passwordValidationResult,
            )
        }
    }

    @Test
    fun 입력이_없다면_아무런에러가_노출되면안된다() {
        textFieldValue.value = ""

        composeTestRule.assertDoesNotExist("비밀번호는 8~16자여야 합니다.")
        composeTestRule.assertDoesNotExist("비밀번호는 영문과 숫자를 포함해야 합니다.")
    }

    @Test
    fun 비밀번호가_8에서_16자이내이며_영문과_숫자를_포함할_때_에러가_없다() {
        textFieldValue.value = "Password123"

        composeTestRule.assertDoesNotExist("비밀번호는 8~16자여야 합니다.")
        composeTestRule.assertDoesNotExist("비밀번호는 영문과 숫자를 포함해야 합니다.")
    }


    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        textFieldValue.value = "short"

        composeTestRule.assertExists("비밀번호는 8~16자여야 합니다.")
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        textFieldValue.value = "Password"

        composeTestRule.assertExists("비밀번호는 영문과 숫자를 포함해야 합니다.")
    }
}
