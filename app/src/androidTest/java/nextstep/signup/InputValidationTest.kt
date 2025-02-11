package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import kotlinx.coroutines.launch
import nextstep.signup.ui.screen.EmailTextField
import nextstep.signup.ui.screen.PasswordConfirmTextField
import nextstep.signup.ui.screen.PasswordTextField
import nextstep.signup.ui.screen.SignUpButton
import nextstep.signup.ui.screen.UsernameTextField
import nextstep.signup.ui.utils.ValidateUtils
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val username = mutableStateOf<String>("")
    private val email = mutableStateOf<String>("")
    private val password = mutableStateOf<String>("")
    private val confirmPassword = mutableStateOf<String>("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            Scaffold(
                snackbarHost = { SnackbarHost(hostState = snackbarHostState) },
            ) {
                Column(
                    modifier = Modifier.padding(it),
                ) {
                    UsernameTextField(
                        userName = username.value,
                        onUsernameChange = { username.value = it },
                        modifier = Modifier.testTag("Username")
                    )
                    EmailTextField(
                        email = email.value,
                        onEmailChange = { email.value = it },
                        modifier = Modifier.testTag("Email")
                    )
                    PasswordTextField(
                        password = password.value,
                        onPasswordChange = { password.value = it },
                        modifier = Modifier.testTag("Password")
                    )
                    PasswordConfirmTextField(
                        password = password.value,
                        confirmPassword = confirmPassword.value,
                        onPasswordChange = { confirmPassword.value = it },
                        modifier = Modifier.testTag("ConfirmPassword")
                    )
                    SignUpButton(
                        isEnabled = ValidateUtils.isValidAll(
                            username = username.value,
                            email = email.value,
                            password = password.value,
                            confirmPassword = confirmPassword.value
                        ),
                        modifier = Modifier.testTag("SignUpButton")
                    ) {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(SNACKBAR_MESSAGE)
                        }
                    }
                }
            }
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        val username = "김컴포즈"

        // then
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(username)

        composeTestRule.onNodeWithTag("Username")
            .assert(!hasText(USERNAME_LENGTH_ERROR))
    }

    @Test
    fun 사용자_이름이_2에서_5자_외인_경우_에러메시지가_노출된다() {
        //when
        val usernameUnder = "김"
        val usernameOver = "김컴포즈입니다"

        //then
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameUnder)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_LENGTH_ERROR))
            .assertExists()

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Username")
            .performTextClearance()

        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameOver)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_LENGTH_ERROR))
            .assertExists()
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함이_됐을떄_에러메시지가_노출된다() {
        // when
        val usernameIncludeNum = "김123"
        val usernameIncludeSign = "김!@#"
        val usernameIncludeNumAndSign = "김12#"

        // then
        /** 사용자 이름에 숫자가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameIncludeNum)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Username")
            .performTextClearance()

        /** 사용자 이름에 기호가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameIncludeSign)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Username")
            .performTextClearance()

        /** 사용자 이름에 숫자와 기호가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameIncludeNumAndSign)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_REGEX_ERROR))
    }

    @Test
    fun 이메일_형식이_올바라야_한다() {
        // when
        val email = "test@test.net"

        // then
        composeTestRule.onNodeWithTag("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithTag("Email")
            .assert(!hasText(EMAIL_REGEX_ERROR))
    }

    @Test
    fun 이메일_형식이_올바르지_않은_경우_오류메시지가_노출된다() {
        // when
        val email = "test@net"

        // then
        composeTestRule.onNodeWithTag("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithTag("Email")
            .assert(hasText(EMAIL_REGEX_ERROR))
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        val password = "12345678"
        val passwordLengthOver = "12345678901234567"

        // then
        /** 비밀번호가 8~16자 이내인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("Password")
            .assert(!hasText(PASSWORD_LENGTH_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Password")
            .performTextClearance()

        /** 비밀번호가 8~16자 외인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(passwordLengthOver)

        composeTestRule.onNodeWithTag("Password")
            .assert(hasText(PASSWORD_LENGTH_ERROR))
    }

    @Test
    fun 비밀번호가_8에서_16자_외인_경우_오류메시지가_노출된다() {
        // when
        val passwordUnder = "1234567"
        val passwordOver = "12345678901234567"

        // then
        /** 비밀번호가 8자 미만인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(passwordUnder)

        composeTestRule.onNodeWithTag("Password")
            .assert(hasText(PASSWORD_LENGTH_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Password")
            .performTextClearance()

        /** 비밀번호가 16자 초과인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(passwordOver)

        composeTestRule.onNodeWithTag("Password")
            .assert(hasText(PASSWORD_LENGTH_ERROR))
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        // when
        val password = "1234abcd"

        // then
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("Password")
            .assert(!hasText(PASSWORD_REGEX_ERROR))
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_오류메시지가_노출된다() {
        // when
        val passwordChar = "fdsfdabcd"
        val passwordNum = "12341234"

        // then
        /** 비밀번호에 영문만 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(passwordChar)

        composeTestRule.onNodeWithTag("Password")
            .assert(hasText(PASSWORD_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Password")
            .performTextClearance()

        /** 비밀번호에 숫자만 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(passwordNum)

        composeTestRule.onNodeWithTag("Password")
            .assert(hasText(PASSWORD_REGEX_ERROR))
    }

    @Test
    fun 비밀번호가_일치해야_한다() {
        // when
        val password = "1234abcd"
        val confirmPassword = "1234abcd"

        // then
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .assert(!hasText(PASSWORD_CONFIRM_ERROR))
    }

    @Test
    fun 비밀번호가_일치하지_않는_경우_오류메시지가_출력된다() {
        // when
        val password = "1234abcd"
        val confirmPassword = "1234abcde"

        // then
        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .assert(hasText(PASSWORD_CONFIRM_ERROR))
    }

    @Test
    fun 모든_유효성을_만족하면_버튼이_활성화된다() {
        // when
        val username = "김컴포즈"
        val email = "test@test.com"
        val password = "qwer1234"
        val confirmPassword = "qwer1234"

        // then
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(username)

        composeTestRule.onNodeWithTag("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("SignUpButton")
            .assertIsEnabled()
    }

    @Test
    fun 모든_유효성을_만족하지_못하면_버튼이_활성화되지_않는다() {
        // when
        val username = "김컴포즈"
        val email = "test@test.com"
        val password = "qwer12345"
        val confirmPassword = "qwer1234"

        // then
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(username)

        composeTestRule.onNodeWithTag("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithTag("Password")
            .performTextInput(password)

        composeTestRule.onNodeWithTag("ConfirmPassword")
            .performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("SignUpButton")
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_버튼을_클릭하면_스낵바가_출력된다() {
        // when
        val username = "김컴포즈"
        val email = "test@test.com"
        val password = "qwer1234"
        val confirmPassword = "qwer1234"

        composeTestRule.onNodeWithTag("Username").performTextInput(username)
        composeTestRule.onNodeWithTag("Email").performTextInput(email)
        composeTestRule.onNodeWithTag("Password").performTextInput(password)
        composeTestRule.onNodeWithTag("ConfirmPassword").performTextInput(confirmPassword)

        composeTestRule.onNodeWithTag("SignUpButton").performClick()
        composeTestRule.onNodeWithText(SNACKBAR_MESSAGE).assertIsDisplayed()
    }

    companion object {
        const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        const val USERNAME_REGEX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."

        const val EMAIL_REGEX_ERROR = "이메일 형식이 올바르지 않습니다."

        const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        const val PASSWORD_REGEX_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        const val PASSWORD_CONFIRM_ERROR = "비밀번호가 일치하지 않습니다."

        const val SNACKBAR_MESSAGE = "회원가입 완료"
    }
}