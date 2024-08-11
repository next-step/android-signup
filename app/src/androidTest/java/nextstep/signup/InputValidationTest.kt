package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
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
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given
        setup(USERNAME, TextFieldType.Username)

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("김컴포즈")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        setup(USERNAME, TextFieldType.Username)

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("김컴포즈입니다")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // given
        setup(USERNAME, TextFieldType.Username)

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("James1")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_기호가_포함되면_에러메시지가_노출된다() {
        // given
        setup(USERNAME, TextFieldType.Username)

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("James!")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // given
        setup(EMAIL, TextFieldType.Email)

        // when
        composeTestRule.onNodeWithText(EMAIL).performTextInput("hello")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르게_입력되어야_한다() {
        // given
        setup(EMAIL, TextFieldType.Email)

        // when
        composeTestRule.onNodeWithText(EMAIL).performTextInput("hello@google.com")

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_올바르게_입력되어야_한다() {
        // given
        setup(PASSWORD, TextFieldType.Password)

        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
            .assertDoesNotExist()

        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // given
        setup(PASSWORD, TextFieldType.Password)
        val passwords =
            listOf("q1w2e3r4", "q1w2e3r4q1w2e3r4")

        passwords.forEach { password ->
            // when
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
                .assertDoesNotExist()
        }
    }

    @Test
    fun 비밀번호는_8에서_16자가_아니면_에러메시지가_노출된다() {
        // given
        setup(PASSWORD, TextFieldType.Password)
        val passwords =
            listOf("q1w2e3", "q1w2e3r4q1w2e3r45")

        passwords.forEach { password ->
            // when
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_LENGTH_ERROR)
                .assertExists()
        }
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        // given
        setup(PASSWORD, TextFieldType.Password)

        // when
        composeTestRule.onNodeWithText(PASSWORD).performTextInput("q1w2e3r4")

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // given
        setup(PASSWORD, TextFieldType.Password)
        val passwords =
            listOf("qqqqwwww", "11112222")

        // when
        passwords.forEach { password ->
            composeTestRule.onNodeWithText(PASSWORD).performTextClearance()
            composeTestRule.onNodeWithText(PASSWORD).performTextInput(password)

            // then
            composeTestRule
                .onNodeWithText(PASSWORD_INVALID_ERROR)
                .assertExists()
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
                    signUpScreen.SignUpHelperText(message = PASSWORD_DO_NOT_MATCH_ERROR)
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
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_INVALID_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_INVALID_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_INVALID_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_DO_NOT_MATCH_ERROR = "비밀번호가 일치하지 않습니다."

        private const val USERNAME = "Username"
        private const val EMAIL = "Email"
        private const val PASSWORD = "Password"
        private const val PASSWORD_CONFIRM = "Password Confirm"
    }
}
