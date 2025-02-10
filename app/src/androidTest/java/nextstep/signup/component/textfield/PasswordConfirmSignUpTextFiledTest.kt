package nextstep.signup.component.textfield

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.components.SignUpTextField
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.PasswordConfirmState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmSignUpTextFiledTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = "abcd1234"
    private val passwordConfirm: MutableState<PasswordConfirmState> =
        mutableStateOf(PasswordConfirmState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextField(
                value = passwordConfirm.value.passwordConfirm,
                onValueChange = { passwordConfirm.value = PasswordConfirm(it).toUiState(password) },
                keyboardType = KeyboardType.Password,
                isError = passwordConfirm.value.isError,
                supportingText = passwordConfirm.value.supportingText,
                needHide = true,
                label = "Password Confirm",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않으면_에러메시지가_표시된다() {
        // when
        passwordConfirm.value = PasswordConfirm("abcd123").toUiState(password)

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText("비밀번호가 일치하지 않습니다."))
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하면_에러메시지가_표시되지_않는다() {
        // when
        passwordConfirm.value = PasswordConfirm("abcd1234").toUiState(password)

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(""))
    }

    companion object {
        private const val TAG_SIGN_UP_TEXT_FIELD = "SignUpTextField"
    }
}
