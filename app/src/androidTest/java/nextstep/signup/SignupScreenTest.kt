package nextstep.signup

import androidx.compose.ui.test.SemanticsNodeInteraction
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signup.SignUpScreen
import nextstep.signup.ui.signup.SignupInvalidationState.EMAIL_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_CONFIRM_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.PASSWORD_RULE_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_LENGTH_INVALIDATION
import nextstep.signup.ui.signup.SignupInvalidationState.USERNAME_RULE_INVALIDATION
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

    @Before
    fun setup() {
        composeTestRule.apply {
            setContent { SignUpScreen() }
            userNameTextField = onNodeWithText("Username")
            emailTextField = onNodeWithText("Email")
            passwordTextField = onNodeWithText("Password")
            passwordConfirmTextField = onNodeWithText("Password Confirm")
        }
    }

    @Test
    fun 사용자_이름이_2자보다_적을_경우_에러메시지가_출력된다() {
        // given:
        val username = "산"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithText(USERNAME_LENGTH_INVALIDATION.message).assertExists()
    }

    @Test
    fun 사용자_이름이_5자보다_많을_경우_에러메시지가_출력된다() {
        // given:
        val username = "산군산군산군"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithText(USERNAME_LENGTH_INVALIDATION.message).assertExists()
    }

    @Test
    fun 사용자_이름에_숫자가_포함될_경우_에러메시지가_출력된다() {
        // given:
        val username: String = "산군1"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithText(USERNAME_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함될_경우_에러메시지가_출력된다() {
        // given:
        val username: String = "산군@"

        // when:
        userNameTextField.performTextInput(username)

        // then:
        composeTestRule.onNodeWithText(USERNAME_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_에러메시지가_출력된다_1() {
        // given:
        val email = "s9hn"

        // when:
        emailTextField.performTextInput(email)

        // then:
        composeTestRule.onNodeWithText(EMAIL_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_에러메시지가_출력된다_2() {
        // given:
        val email = "s9hn@github"

        // when:
        emailTextField.performTextInput(email)

        // then:
        composeTestRule.onNodeWithText(EMAIL_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 비밀번호가_8자보다_적은_경우_에러메시지가_출력된다() {
        // given:
        val password: String = "1234567"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_LENGTH_INVALIDATION.message).assertExists()
    }

    @Test
    fun 비밀번호가_16자보다_많은_경우_에러메시지가_출력된다() {
        // given:
        val password: String = "12345678123456789"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_LENGTH_INVALIDATION.message).assertExists()
    }

    @Test
    fun 비밀번호에_영어가_포함되지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password: String = "12345678"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 비밀번호에_숫자가_포함되지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password: String = "abcdefgh"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_RULE_INVALIDATION.message).assertExists()
    }

    @Test
    fun 비밀번호에_영문과_숫자가_반드시_포함되어야_한다() {
        // given:
        val password: String = "abcd1234"

        // when:
        passwordTextField.performTextInput(password)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_RULE_INVALIDATION.message).assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인은_비밀번호와_일치해야_한다() {
        // given:
        val password = "abcd1234"
        val passwordConfirm: String = password

        // when:
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM_RULE_INVALIDATION.message)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인이_비밀번호와_일치하지_않을_경우_에러메시지가_출력된다() {
        // given:
        val password = "abcd1234"
        val passwordConfirm: String = "abcd1233"

        // when:
        passwordTextField.performTextInput(password)
        passwordConfirmTextField.performTextInput(passwordConfirm)

        // then:
        composeTestRule.onNodeWithText(PASSWORD_CONFIRM_RULE_INVALIDATION.message).assertExists()
    }
}
