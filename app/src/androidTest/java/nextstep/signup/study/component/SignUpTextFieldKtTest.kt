package nextstep.signup.study.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.component.SignUpFormComponent
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

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpFormComponent(userName = username.value,
                email = email.value,
                password = password.value,
                passwordConfirm = passwordConfirm.value,
                onUserNameChange = { username.value = it },
                onEmailChange = { email.value = it },
                onPasswordChange = { password.value = it },
                onPasswordConfirmChange = { passwordConfirm.value = it })
        }
    }

    @Test
    fun 사용자_이름이_2에서_5자면_에러메시지가_노출되지_않는다() {
        // When
        username.value = "컴포즈"

        // Then
        composeTestRule.onNodeWithText(USERNAME_LENGTH_ERROR).assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자_미만이면_에러메시지가_노출된다() {
        // When
        username.value = "안"

        // Then
        composeTestRule.onNodeWithText(USERNAME_LENGTH_ERROR).assertExists()
    }

    @Test
    fun 사용자_이름이_5자_초과면_에러메시지가_노출된다() {
        // When
        username.value = "컴포즈컴포즈"

        // Then
        composeTestRule.onNodeWithText(USERNAME_LENGTH_ERROR).assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        // When
        username.value = "컴포즈1"

        // Then
        composeTestRule.onNodeWithText(USERNAME_INVALID_CHARS_ERROR).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르면_에러메시지가_노출되지_않는다() {
        // When
        email.value = "test@test.com"

        // Then
        composeTestRule.onNodeWithText(EMAIL_INVALID_ERROR).assertDoesNotExist()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // When
        email.value = "invalid-email"

        // Then
        composeTestRule.onNodeWithText(EMAIL_INVALID_ERROR).assertExists()
    }

    @Test
    fun 비밀번호가_8에서_16자이고_영문과_숫자를_포함하면_에러메시지가_노출되지_않는다() {
        // When
        password.value = "password123"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_LENGTH_ERROR).assertDoesNotExist()
        composeTestRule.onNodeWithText(PASSWORD_COMPLEXITY_ERROR).assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자_미만이면_에러메시지가_노출된다() {
        // When
        password.value = "pass1"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_LENGTH_ERROR).assertExists()
    }

    @Test
    fun 비밀번호가_16자_초과면_에러메시지가_노출된다() {
        // When
        password.value = "verylongpassword123456"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_LENGTH_ERROR).assertExists()
    }

    @Test
    fun 비밀번호에_영문과_숫자가_포함되지_않으면_에러메시지가_노출된다() {
        // When
        password.value = "password"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_COMPLEXITY_ERROR).assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하면_에러메시지가_노출되지_않는다() {
        // When
        password.value = "password123"
        passwordConfirm.value = "password123"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_MISMATCH_ERROR).assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않으면_에러메시지가_노출된다() {
        // When
        password.value = "password123"
        passwordConfirm.value = "differentpassword123"

        // Then
        composeTestRule.onNodeWithText(PASSWORD_MISMATCH_ERROR).assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_CHARS_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_INVALID_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        private const val PASSWORD_COMPLEXITY_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}