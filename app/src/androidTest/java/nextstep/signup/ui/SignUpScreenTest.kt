package nextstep.signup.ui

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithTag
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.platform.app.InstrumentationRegistry
import nextstep.signup.R
import nextstep.signup.ui.component.EmailValidation.EmailValidationResult
import nextstep.signup.ui.component.PasswordConfirmValidation.PasswordConfirmValidationResult
import nextstep.signup.ui.component.PasswordValidation.PasswordValidationResult
import nextstep.signup.ui.component.UsernameValidation.UsernameValidationResult
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val uiState = mutableStateOf(SignUpUiState.DEFAULT)
    private val usernameValidationResult =
        mutableStateOf<UsernameValidationResult>(UsernameValidationResult.Empty)
    private val emailValidationResult =
        mutableStateOf<EmailValidationResult>(EmailValidationResult.Empty)
    private val passwordValidationResult =
        mutableStateOf<PasswordValidationResult>(PasswordValidationResult.Empty)
    private val passwordConfirmValidationResult =
        mutableStateOf<PasswordConfirmValidationResult>(PasswordConfirmValidationResult.Empty)

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(
                uiState = uiState.value,
                onUsernameChange = { },
                onEmailChange = { },
                onPasswordChange = { },
                onPasswordConfirmChange = { },
                usernameValidationResult = usernameValidationResult.value,
                emailValidationResult = emailValidationResult.value,
                passwordValidationResult = passwordValidationResult.value,
                passwordConfirmValidationResult = passwordConfirmValidationResult.value,
            )
        }
    }

    @Test
    fun 모든_필드가_정상_입력되어_있을_때_회원가입_버튼을_활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsEnabled()
    }

    @Test
    fun 유저이름이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Empty
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Empty
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Empty
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호_확인이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Empty

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_다를_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value =
            PasswordConfirmValidationResult.PasswordNotMatchError

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 유저이름이_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.UsernameLengthError
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.EmailFormatError
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.PasswordFormatError
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_버튼을_누르면_회원가입이_완료되었다고_스낵바가_노출된다() {
        // given
        usernameValidationResult.value = UsernameValidationResult.Success
        emailValidationResult.value = EmailValidationResult.Success
        passwordValidationResult.value = PasswordValidationResult.Success
        passwordConfirmValidationResult.value = PasswordConfirmValidationResult.Success

        // when
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsEnabled()
            .performClick()

        // then
        composeTestRule.waitUntil(timeoutMillis = 1000) {
            composeTestRule
                .onAllNodesWithTag(context.getString(R.string.test_tag_snackbar))
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_snackbar))
            .assertIsDisplayed()
    }
}
