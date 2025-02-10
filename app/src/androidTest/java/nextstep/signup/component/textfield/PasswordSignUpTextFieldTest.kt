package nextstep.signup.component.textfield

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.components.SignUpTextField
import nextstep.signup.domain.Password
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.PasswordState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class PasswordSignUpTextFieldInvalidLengthTest(
    private val input: String,
    private val errorMessage: String,
) {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val passwordState: MutableState<PasswordState> = mutableStateOf(PasswordState())

    @Test
    fun 비밀번호의_글자수가_8글자보다_작거나_16글자보다_크면_에러메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                value = passwordState.value.password,
                onValueChange = { passwordState.value = Password(it).toUiState() },
                isError = passwordState.value.isError,
                supportingText = passwordState.value.supportingText,
                label = "password",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }

        // when
        passwordState.value = Password(input).toUiState()

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(errorMessage))
    }

    companion object {
        private const val TAG_SIGN_UP_TEXT_FIELD = "SignUpTextField"

        @JvmStatic
        @Parameters
        fun getData(): List<Array<String>> {
            return listOf(
                arrayOf("abcd123", "비밀번호는 8~16자여야 합니다."),
                arrayOf("1234567890abcdefg", "비밀번호는 8~16자여야 합니다."),
                arrayOf("abcd1234", ""),
                arrayOf("1234567890abcdef", ""),
            )
        }
    }
}

@RunWith(Parameterized::class)
class PasswordSignUpTextFieldInvalidCharacterTest(
    private val input: String,
    private val errorMessage: String,
) {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val passwordState: MutableState<PasswordState> = mutableStateOf(PasswordState())

    @Test
    fun 비밀번호에_영문과_숫자가_동시에_포함되지_않았다면_에러메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                value = passwordState.value.password,
                onValueChange = { passwordState.value = Password(it).toUiState() },
                isError = passwordState.value.isError,
                supportingText = passwordState.value.supportingText,
                label = "password",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }

        // when
        passwordState.value = Password(input).toUiState()

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(errorMessage))
    }

    companion object {
        private const val TAG_SIGN_UP_TEXT_FIELD = "SignUpTextField"

        @JvmStatic
        @Parameters
        fun getData(): List<Array<String>> {
            return listOf(
                arrayOf("abcdefgh", "비밀번호는 영문과 숫자를 포함해야 합니다."),
                arrayOf("12345678", "비밀번호는 영문과 숫자를 포함해야 합니다."),
                arrayOf("abcd1234김", ""),
                arrayOf("1234abcd🥸", ""),
                arrayOf("abcd1234", ""),
                arrayOf("1q2w3e4r", ""),
            )
        }
    }
}
