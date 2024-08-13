package nextstep.signup

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.runTest
import nextstep.signup.ui.signup.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private lateinit var userNameTextField: SemanticsNodeInteraction
    private lateinit var emailTextField: SemanticsNodeInteraction
    private lateinit var passwordTextField: SemanticsNodeInteraction
    private lateinit var passwordConfirmTextField: SemanticsNodeInteraction
    private lateinit var createAccountButton: SemanticsNodeInteraction

    @Before
    fun setup() {
        composeTestRule.apply {
            setContent {
                SignupScreen(
                    onSignupClick = {},
                    modifier = Modifier,
                )
            }
            userNameTextField = onNodeWithContentDescription(USERNAME_CONTENT_DESCRIPTION)
            emailTextField = onNodeWithContentDescription(EMAIL_CONTENT_DESCRIPTION)
            passwordTextField = onNodeWithContentDescription(PASSWORD_CONTENT_DESCRIPTION)
            passwordConfirmTextField = onNodeWithContentDescription(
                PASSWORD_CONFIRM_CONTENT_DESCRIPTION
            )
            createAccountButton = onNodeWithContentDescription(
                CREATE_ACCOUNT_BUTTON_CONTENT_DESCRIPTION
            )
        }
    }

    @Test
    fun 사용자_이름이_2자보다_적을_경우_에러메시지가_출력된다() {
        // given:
        val username = "산"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithContentDescription(USERNAME_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_5자보다_많을_경우_에러메시지가_출력된다() {
        // given:
        val username = "산군산군산군"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithContentDescription(USERNAME_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_포함될_경우_에러메시지가_출력된다() {
        // given:
        val username = "산군1"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithContentDescription(USERNAME_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함될_경우_에러메시지가_출력된다() {
        // given:
        val username = "산군@"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithContentDescription(USERNAME_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_에러메시지가_출력된다_1() {
        // given:
        val email = "s9hn"

        // when:
        emailTextField.performTextInput(email)

        // then:
        composeTestRule.onNodeWithContentDescription(EMAIL_ERROR_CONTENT_DESCRIPTION).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_에러메시지가_출력된다_2() {
        // given:
        val email = "s9hn@github"

        // when:
        emailTextField.performTextInput(email)

        // then:
        composeTestRule.onNodeWithContentDescription(EMAIL_ERROR_CONTENT_DESCRIPTION).assertExists()
    }

    @Test
    fun 비밀번호가_8자보다_적은_경우_에러메시지가_출력된다() {
        // given:
        val password = "1234567"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자보다_많은_경우_에러메시지가_출력된다() {
        // given:
        val password = "12345678123456789"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 비밀번호에_영어가_포함되지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password = "12345678"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 비밀번호에_숫자가_포함되지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password = "abcdefgh"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 비밀번호에_영문과_숫자가_반드시_포함되어야_한다() {
        // given:
        val password = "abcd1234"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_ERROR_CONTENT_DESCRIPTION)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인은_비밀번호와_일치해야_한다() {
        // given:
        val password = "abcd1234"
        val passwordConfirm = password

        // when:
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_CONFIRM_ERROR_CONTENT_DESCRIPTION)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인이_비밀번호와_일치하지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password = "abcd1234"
        val passwordConfirm = "abcd1233"

        // when:
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        composeTestRule.onNodeWithContentDescription(PASSWORD_CONFIRM_ERROR_CONTENT_DESCRIPTION)
            .assertExists()
    }

    @Test
    fun 버튼의_기본값은_비활성화이다() {
        // given:
        // when:
        // then:
        createAccountButton.assertIsNotEnabled()
    }

    @Test
    fun 어느하나_조건을_만족하지_못해도_버튼이_활성화_되지_않는다() {
        // given:
        val username = "산군"
        val email = "s9hn@github.com"
        val password = "abcd1234"
        val passwordConfirm = "abcd1233"

        // when:
        userNameTextField.performTextInput(username)
        emailTextField.performTextInput(email)
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        createAccountButton.assertIsNotEnabled()
    }

    @Test
    fun 회원가입_모든_조건을_만족하면_버튼이_활성화된다() {
        // given:
        val username = "산군"
        val email = "s9hn@github.com"
        val password = "abcd1234"
        val passwordConfirm = "abcd1234"

        // when:
        userNameTextField.performTextInput(username)
        emailTextField.performTextInput(email)
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        createAccountButton.assertIsEnabled()
    }

    companion object {
        private const val USERNAME_CONTENT_DESCRIPTION = "Username"
        private const val EMAIL_CONTENT_DESCRIPTION = "Email"
        private const val PASSWORD_CONTENT_DESCRIPTION = "Password"
        private const val PASSWORD_CONFIRM_CONTENT_DESCRIPTION = "Password Confirm"
        private const val USERNAME_ERROR_CONTENT_DESCRIPTION = "UsernameTextFieldError"
        private const val EMAIL_ERROR_CONTENT_DESCRIPTION = "EmailTextFieldError"
        private const val PASSWORD_ERROR_CONTENT_DESCRIPTION = "PasswordTextFieldError"
        private const val PASSWORD_CONFIRM_ERROR_CONTENT_DESCRIPTION =
            "PasswordConfirmTextFieldError"
        private const val CREATE_ACCOUNT_BUTTON_CONTENT_DESCRIPTION = "CreateAccountButton"
    }
}
