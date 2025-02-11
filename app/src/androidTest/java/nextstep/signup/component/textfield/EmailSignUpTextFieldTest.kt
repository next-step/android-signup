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
import nextstep.signup.domain.Email
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.Parameterized
import org.junit.runners.Parameterized.Parameters

@RunWith(Parameterized::class)
class EmailSignUpTextFieldInvalidFormTest(
    private val input: String,
    private val errorMessage: String,
) {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val inputFieldState: MutableState<InputFieldState> =
        mutableStateOf(Email().toUiState())

    @Test
    fun email형식을_따르지_않으면_에러메시지가_표시된다() {
        // given
        composeTestRule.setContent {
            SignUpTextField(
                value = inputFieldState.value.input,
                onValueChange = { inputFieldState.value = Email(it).toUiState() },
                isError = inputFieldState.value.isError,
                supportingText = inputFieldState.value.supportingText,
                label = "email",
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }

        // when
        inputFieldState.value = Email(input).toUiState()

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
                arrayOf("kimCompose", "이메일 형식이 올바르지 않습니다."),
                arrayOf("KimCompose@", "이메일 형식이 올바르지 않습니다."),
                arrayOf("KimCompose@.", "이메일 형식이 올바르지 않습니다."),
                arrayOf("KimCompose@gmail.", "이메일 형식이 올바르지 않습니다."),
                arrayOf("KimCompose@gmail.com", ""),
                arrayOf("KimCompose@hanmail.net", ""),
            )
        }
    }
}
