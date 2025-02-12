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
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class UsernameSignUpTextFieldInvalidLengthTest(
    private val input: String,
    private val errorMessage: String,
) {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val usernameState: MutableState<InputFieldState> =
        mutableStateOf(Username().toUiState())

    @Test
    fun username의_글자수가_2글자보다_작거나_5글자보다_크면_에러메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                value = usernameState.value.input,
                onValueChange = { usernameState.value = Username(it).toUiState() },
                isError = usernameState.value.isError,
                supportingText = usernameState.value.supportingText,
                label = "username",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }

        // when
        usernameState.value = Username(input).toUiState()

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
                arrayOf("김", "이름은 2~5자여야 합니다."),
                arrayOf("김컴포즈임다", "이름은 2~5자여야 합니다."),
                arrayOf("김컴", ""),
                arrayOf("김컴포즈요", ""),
            )
        }
    }
}

@RunWith(Parameterized::class)
class UsernameSignUpTextFieldInvalidCharacterTest(
    private val input: String,
    private val errorMessage: String,
) {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val usernameState: MutableState<InputFieldState> =
        mutableStateOf(Username().toUiState())

    @Test
    fun username으로_한글과_알파벳이아닌_다른_문자가_입력되면_에러메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                value = usernameState.value.input,
                onValueChange = { usernameState.value = Username(it).toUiState() },
                isError = usernameState.value.isError,
                supportingText = usernameState.value.supportingText,
                label = "username",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }

        // when
        usernameState.value = Username(input).toUiState()

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(errorMessage))
    }

    companion object {
        private const val TAG_SIGN_UP_TEXT_FIELD = "SignUpTextField"

        @JvmStatic
        @Parameters
        fun getData(): Collection<Array<Any>> {
            return listOf(
                arrayOf("🤪", "이름에는 숫자나 기호가 포함될 수 없습니다."),
                arrayOf("김컴포즈1", "이름에는 숫자나 기호가 포함될 수 없습니다."),
                arrayOf("123", "이름에는 숫자나 기호가 포함될 수 없습니다."),
                arrayOf("文相現", "이름에는 숫자나 기호가 포함될 수 없습니다."),
                arrayOf("김컴포즈", ""),
                arrayOf("Compo", ""),
            )
        }
    }
}
