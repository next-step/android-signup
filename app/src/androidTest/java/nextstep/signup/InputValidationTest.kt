package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.view.SignUpHelperText
import nextstep.signup.view.SignUpScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val input = mutableStateOf("")
    private lateinit var signUpScreen: SignUpScreen

    @Before
    fun setUp() {
        signUpScreen = SignUpScreen()
    }

    private fun setup(hint: String, type: TextFieldType) {
        composeTestRule.setContent {
            signUpScreen.SignUpTextField(
                hint = hint,
                type = type,
            )
        }
    }

    @Test
    fun 입력한_두_비밀번호는_동일해야_한다() {
        // given
        composeTestRule.setContent {
            Column {
                signUpScreen.SignUpTextField(
                    hint = PASSWORD,
                    type = TextFieldType.Password,
                    onTextChange = { input.value = it }
                )
                signUpScreen.SignUpTextField(
                    hint = PASSWORD_CONFIRM,
                    type = TextFieldType.Password,
                    onTextChange = { input.value = it }
                )
            }
        }

        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DO_NOT_MATCH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 입력한_두_비밀번호가_일치하지_않으면_에러메시지가_노출된다() {
        // given
        var password by mutableStateOf("")
        var confirmPassword by mutableStateOf("")
        var isMatchPassword by mutableStateOf(true)

        composeTestRule.setContent {
            Column {
                signUpScreen.SignUpTextField(
                    hint = PASSWORD,
                    type = TextFieldType.Password,
                    onTextChange = {
                        password = it
                        isMatchPassword = password == confirmPassword
                    }
                )
                signUpScreen.SignUpTextField(
                    hint = PASSWORD_CONFIRM,
                    type = TextFieldType.Password,
                    onTextChange = {
                        confirmPassword = it
                        isMatchPassword = password == confirmPassword
                    }
                )
                if (!isMatchPassword) {
                    SignUpHelperText(message = PASSWORD_DO_NOT_MATCH_ERROR)
                }
            }
        }

        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r45")
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_DO_NOT_MATCH_ERROR)
            .assertExists()
    }

    companion object {
        private const val PASSWORD_DO_NOT_MATCH_ERROR = "비밀번호가 일치하지 않습니다."
        private const val PASSWORD = "Password"
        private const val PASSWORD_CONFIRM = "Password Confirm"
    }
}
