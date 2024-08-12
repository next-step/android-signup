package nextstep.signup.study

import androidx.compose.runtime.collectAsState
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import kotlinx.coroutines.flow.MutableStateFlow
import nextstep.signup.SignUpUiState
import nextstep.signup.ui.component.EmailInput
import nextstep.signup.ui.component.PasswordConfirmInput
import nextstep.signup.ui.component.PasswordInput
import nextstep.signup.ui.component.UserNameInput
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val signUpUiState = MutableStateFlow(SignUpUiState())

    @Before
    fun setup() {
        composeTestRule.setContent {
            UserNameInput(
                value = signUpUiState.value.username,
                signUpUiState = signUpUiState.collectAsState().value,
                onValueChange = {}
            )

            EmailInput(
                value = signUpUiState.value.email,
                signUpUiState = signUpUiState.collectAsState().value,
                onValueChange = {},

            )

            PasswordInput(
                value = signUpUiState.value.password,
                signUpUiState = signUpUiState.collectAsState().value,
                onValueChange = {}
            )

            PasswordConfirmInput(
                value = signUpUiState.value.passwordConfirm,
                signUpUiState = signUpUiState.collectAsState().value,
                onValueChange = {}
            )
        }
    }

    @Test
    fun 이름은_형식에_맞게_작성되어야_한다() {
        // when
        signUpUiState.value = SignUpUiState(username = "asd")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이름_길이_유효성_검사가_실패하면_오류_메시지가_노출된다() {
        // when
        signUpUiState.value = SignUpUiState(username = "asdfff")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_숫자나_기호가_포함되면_오류_메시지가_노출된다() {
        // when
        signUpUiState.value = SignUpUiState(username = "123!")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일은_형식에_맞게_작성되어야_한다() {
        signUpUiState.value = SignUpUiState(email = "1234@gmail.com")

        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_형식에_맞지_않는다면_에러메시지가_노출된다() {
        signUpUiState.value = SignUpUiState(email = "1234@")

        composeTestRule
            .onNodeWithText(EMAIL_INVALID_FORMAT_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문과_숫자를_포함해야_한다() {
        signUpUiState.value = SignUpUiState(password = "asdf1234")

        composeTestRule
            .onNodeWithText(PASSWORD_VALIDATION_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호의_길이는_맞고_형식이_틀리면_에러메시지가_노출된다() {
        signUpUiState.value = SignUpUiState(password = "12341234")

        composeTestRule
            .onNodeWithText(PASSWORD_VALIDATION_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호의_형식은_맞고_길이가_맞지_않으면_에러메시지가_노출된다() {
        signUpUiState.value = SignUpUiState(password = "1234a")

        composeTestRule
            .onNodeWithText(PASSWORD_VALIDATION_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호의_길이와_형식이_맞지_않으면_에러메시지가_노출된다() {
        signUpUiState.value = SignUpUiState(password = "1234")

        composeTestRule
            .onNodeWithText(PASSWORD_VALIDATION_ERROR)
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값은_일치해야_한다() {
        signUpUiState.value = SignUpUiState(password = "asdf1234", passwordConfirm = "asdf1234")

        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_일치하지_않으면_에러메시지가_노출된다() {
        signUpUiState.value = SignUpUiState(password = "asdf1234", passwordConfirm = "asdf12344")

        composeTestRule
            .onNodeWithText(PASSWORD_MISMATCH_ERROR)
            .assertExists()
    }


    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val EMAIL_INVALID_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
        private const val PASSWORD_VALIDATION_ERROR = "비밀번호는 8~16자여야 합니다.\n비밀번호는 영문과 숫자를 포함해야 합니다."
        private const val PASSWORD_MISMATCH_ERROR = "비밀번호가 일치하지 않습니다."
    }
}
