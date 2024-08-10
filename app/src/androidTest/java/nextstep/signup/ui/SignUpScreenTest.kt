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
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    private val context: Context = InstrumentationRegistry.getInstrumentation().targetContext

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(
                username = username.value,
                email = email.value,
                password = password.value,
                passwordConfirm = passwordConfirm.value,
                onUsernameChange = { username.value = it },
                onEmailChange = { email.value = it },
                onPasswordChange = { password.value = it },
                onPasswordConfirmChange = { passwordConfirm.value = it },
            )
        }
    }

    @Test
    fun 모든_필드가_정상_입력되어_있을_때_회원가입_버튼을_활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail.com"
        password.value = "password12"
        passwordConfirm.value = "password12"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsEnabled()
    }

    @Test
    fun 유저이름이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = ""
        password.value = "password12"
        passwordConfirm.value = "password12"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = ""
        password.value = "password12"
        passwordConfirm.value = "password12"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail.com"
        password.value = ""
        passwordConfirm.value = "password12"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호_확인이_비어있을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail.com"
        password.value = "password12"
        passwordConfirm.value = ""

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_다를_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail.com"
        password.value = "password12"
        passwordConfirm.value = "password1234"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 유저이름이_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "u"
        email.value = "user@yopmail.com"
        password.value = "password12"
        passwordConfirm.value = "password1234"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail"
        password.value = "password12"
        passwordConfirm.value = "password1234"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_유효하지_않을_때_회원가입_버튼을_비활성화한다() {
        // given
        username.value = "user"
        email.value = "user@yopmail"
        password.value = "password12"
        passwordConfirm.value = "password1234"

        // then
        composeTestRule
            .onNodeWithTag(context.getString(R.string.test_tag_btn_sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_버튼을_누르면_회원가입이_완료되었다고_스낵바가_노출된다() {
        // given
        username.value = "user"
        email.value = "user@yopmail.com"
        password.value = "password12"
        passwordConfirm.value = "password12"

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
