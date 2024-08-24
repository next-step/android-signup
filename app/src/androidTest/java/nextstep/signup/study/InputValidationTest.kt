package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.screen.EmailSigneUpTextField
import nextstep.signup.screen.PassWordConfirmSigneUpTextField
import nextstep.signup.screen.PassWordSigneUpTextField
import nextstep.signup.screen.UserNameSigneUpTextField
import nextstep.signup.util.SignUpEmailValidator
import nextstep.signup.util.SignUpPasswordConfirmValidator
import nextstep.signup.util.SignUpPasswordValidator
import nextstep.signup.util.SignUpUserNameValidator
import nextstep.signup.util.SignUpValidSate
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val userNameTextFieldContent = mutableStateOf("")
    private val userNameInputValidState = mutableStateOf(SignUpValidSate.NOTHING)

    private val emailTextFieldContent = mutableStateOf("")
    private val emailInputValidState = mutableStateOf(SignUpValidSate.NOTHING)

    private val passWordTextFieldContent = mutableStateOf("")
    private val passWordInputValidState = mutableStateOf(SignUpValidSate.NOTHING)

    private val passWordConfirmTextFieldContent = mutableStateOf("")
    private val passWordConfirmInputValidState = mutableStateOf(SignUpValidSate.NOTHING)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            UserNameSigneUpTextField(
                textContent = userNameTextFieldContent.value,
                onTextValueChange = { userNameTextFieldContent.value = it },
                signUpInputValidState = userNameInputValidState.value,
            )
            EmailSigneUpTextField(
                textContent = emailTextFieldContent.value,
                onTextValueChange = { emailTextFieldContent.value = it },
                signUpInputValidState = emailInputValidState.value,
            )
            PassWordSigneUpTextField(
                textContent = passWordTextFieldContent.value,
                onTextValueChange = { passWordTextFieldContent.value = it },
                signUpInputValidState = passWordInputValidState.value,
            )
            PassWordConfirmSigneUpTextField(
                textContent = passWordConfirmTextFieldContent.value,
                onTextValueChange = { passWordConfirmTextFieldContent.value = it },
                signUpInputValidState = passWordConfirmInputValidState.value,
            )
        }
    }

    @Test
    fun `유저_이름이_2에서_5자이면_성공이다`() {
        // when & when
        userNameInputValidState.value = SignUpValidSate.VALID

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `유저_이름이_2글자_미만일_5초과_일때_에러메시지가_노출된다`() {
        // when
        userNameInputValidState.value = SignUpValidSate.ERROR_USER_NAME_LENGTH

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }


    @Test
    fun `유저_이름에_숫자나_기호가_들어가면_에러메세지_노출된다`() {
        // when
        userNameInputValidState.value = SignUpValidSate.ERROR_USER_NAME_REGEX

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun `이메일_형식이_아니면_에러메세지_노출된다`() {
        // when
        emailInputValidState.value = SignUpValidSate.ERROR_EMAIL_REGEX

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }


    @Test
    fun `비밀번호는_8에서_16자_사이어야_한다`() {
        // when
       passWordInputValidState.value = SignUpValidSate.ERROR_PASSWORD_LENGTH

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun `비밀번호에_영문_숫자_조합이_들어가지_않으면_에러메세지_노출된다`() {
        // when
        passWordInputValidState.value = SignUpValidSate.ERROR_PASSWORD_REGEX

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }


    @Test
    fun `비밀번호_확인창에_작성한_비밀번호_값이_안들어가면_에러메세지_노출된다`() {
        // when
       passWordConfirmInputValidState.value = SignUpValidSate.ERROR_PASSWORD_CONFIRM

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}