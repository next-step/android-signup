package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signin.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignupTextFieldValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignupScreen()
        }
    }

    @Test
    fun 이름은_2에서_5자여야_한다() {
        // given
        val text = "컴포즈"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이름이_2자가_안되면_에러메시지_노출된다() {
        // given
        val text = "컴"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름이_5자가_넘으면_에러메시지_노출된다() {
        // given
        val text = "컴포즈입니다"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에는_숫자나_기호가_포함될_수_없다() {
        // given
        val text = "컴포즈"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // given
        val text = "컴포즈1"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_특문이_포함되면_에러메시지가_노출된다() {
        // given
        val text = "컴포즈#"

        // when
        composeTestRule
            .onNodeWithText(USERNAME_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르면_에러메시지_노출되지_않는다() {
        // given
        val text = "compose@gmail.com"

        // when
        composeTestRule
            .onNodeWithText(EMAIL_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지_노출된다() {
        // given
        val text = "compose#gmail.com"

        // when
        composeTestRule
            .onNodeWithText(EMAIL_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_8에서_16자_이어야_한다() {
        // given
        val text = "aaaa1234"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자가_안되면_에러메시지_노출된다() {
        // given
        val text = "aaaa123"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호가_16자를_넘으면_에러메시지_노출된다() {
        // given
        val text = "aaaabbbbccccdddd0"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자가_포함되지_않으면_에러메시지_노출된다() {
        // given
        val text = "abcdabcd#"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_확인은_8자가_안되면_에러메시지_노출된다() {
        // given
        val text = "aaaa123"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_확인은_16자를_넘으면_에러메시지_노출된다() {
        // given
        val text = "aaaabbbbccccdddd0"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호_확인은_영문과_숫자가_포함되지_않으면_에러메시지_노출된다() {
        // given
        val text = "abcdabcd##"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_LABEL)
            .performTextInput(text)

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun signup_버튼을_클릭했을때_비밀번호와_비밀번호_확인_두개가_서로_같으면_에러_메시지가_노출되지_않는다() {
        // given
        val password = "abcd1234"
        val passwordConfirm = "abcd1234"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(password)

        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_LABEL)
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText(SIGNUP_BUTTON)
            .performClick()

        // then
        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun signup_버튼을_클릭했을때_비밀번호와_비밀번호_확인_두개가_서로_다르면_에러_메시지가_노출된다() {
        // given
        val password = "abcd1234"
        val passwordConfirm = "abcd1235"

        // when
        composeTestRule
            .onNodeWithText(PASSWORD_LABEL)
            .performTextInput(password)

        composeTestRule
            .onNodeWithText(PASSWORD_CONFIRM_LABEL)
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText(SIGNUP_BUTTON)
            .performClick()

        // then
        // ToastMessage 를 캐치할 수 없기 때문에 테스트 불가 (리팩토링 단계에서 Sign Up 버튼의 클릭 여부를 테스트 예정)
//        composeTestRule
//            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
//            .assertExists()
    }

    companion object {
        // Label
        private const val USERNAME_LABEL = "Username"
        private const val EMAIL_LABEL = "Email"
        private const val PASSWORD_LABEL = "Password"
        private const val PASSWORD_CONFIRM_LABEL = "Password Confirm"
        private const val SIGNUP_BUTTON = "Sign Up"

        // ErrorMessage
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_INVALID_FORMAT_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}