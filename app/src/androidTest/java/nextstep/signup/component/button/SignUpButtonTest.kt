package nextstep.signup.component.button

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.InputFieldChangeListener
import nextstep.signup.InputFieldKey
import nextstep.signup.SignUpScreen
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            var inputFields: Map<InputFieldKey, InputFieldState> by remember {
                mutableStateOf(
                    mapOf(
                        InputFieldKey.USERNAME to Username().toUiState(),
                        InputFieldKey.EMAIL to Email().toUiState(),
                        InputFieldKey.PASSWORD to Password().toUiState(),
                        InputFieldKey.PASSWORD_CONFIRM to PasswordConfirm().toUiState()
                    )
                )
            }
            val inputFieldChangeListeners: Map<InputFieldKey, InputFieldChangeListener> by remember {
                mutableStateOf(
                    mapOf(
                        InputFieldKey.USERNAME to InputFieldChangeListener { username: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[InputFieldKey.USERNAME] = Username(username).toUiState()
                            }
                        },
                        InputFieldKey.EMAIL to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[InputFieldKey.EMAIL] = Email(it).toUiState()
                            }
                        },
                        InputFieldKey.PASSWORD to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[InputFieldKey.PASSWORD] = Password(it).toUiState()
                                this[InputFieldKey.PASSWORD_CONFIRM] = PasswordConfirm(
                                    it,
                                    this[InputFieldKey.PASSWORD_CONFIRM]?.input ?: ""
                                ).toUiState()
                            }
                        },
                        InputFieldKey.PASSWORD_CONFIRM to InputFieldChangeListener { it: String ->
                            inputFields = inputFields.toMutableMap().apply {
                                this[InputFieldKey.PASSWORD_CONFIRM] =
                                    PasswordConfirm(
                                        this[InputFieldKey.PASSWORD]?.input ?: "",
                                        it
                                    ).toUiState()
                            }
                        },
                    )
                )
            }

            SignUpScreen(
                inputFields = inputFields,
                inputFieldChangeListeners = inputFieldChangeListeners,
                onSignUpButtonClick = {},
                modifier = Modifier
                    .fillMaxSize()
            )
        }
    }

    @Test
    fun 가입_버튼의_초기상태는_비활성화다() {
        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_입력폼을_유효하게_입력하면_가입_버튼이_활성화_된다() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("문상현")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("no1msh1217@gmail.com")
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("asdf1234")
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("asdf1234")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 하나의_입력폼이라도_유효하지_않다면_가입_버튼은_비활성화다() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("문상현")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("no1msh1217@gmail.com")
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("asdf1234")
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("asdf123") // 비밀번호와 일치하지 않음

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
