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
            userNameInputValidState.value =
                SignUpUserNameValidator().getValidState(userNameTextFieldContent.value)

            EmailSigneUpTextField(
                textContent = emailTextFieldContent.value,
                onTextValueChange ={ emailTextFieldContent.value = it} ,
                signUpInputValidState = emailInputValidState.value,
            )
            emailInputValidState.value =
                SignUpEmailValidator().getValidState(emailTextFieldContent.value)


            PassWordSigneUpTextField(
                textContent = passWordTextFieldContent.value,
                onTextValueChange =  { passWordTextFieldContent.value = it },
                signUpInputValidState =  passWordInputValidState.value,
            )
            passWordInputValidState.value =
                SignUpPasswordValidator().getValidState(passWordTextFieldContent.value)

            PassWordConfirmSigneUpTextField(
                textContent = passWordConfirmTextFieldContent.value,
                onTextValueChange =  { passWordConfirmTextFieldContent.value = it },
                signUpInputValidState =  passWordConfirmInputValidState.value,
            )
            passWordConfirmInputValidState.value =
                SignUpPasswordConfirmValidator(
                    password = passWordTextFieldContent.value
                ).getValidState(passWordConfirmTextFieldContent.value)
        }
    }

    @Test
    fun `유저_이름이_2에서_5자여야_한다`() {
        // when
        userNameTextFieldContent.value = "이이이"

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `유저_이름이_2글자_미만일_5초과_일때_에러메시지가_노출된다`() {
        // when
        userNameTextFieldContent.value = "이"

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }


    @Test
    fun `유저_이름에_숫자나_기호가_들어가면_에러메세지_노출된다`(){
        // when
        userNameTextFieldContent.value = "이33"

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun `이메일_형식이_아니면_에러메세지_노출된다`(){
        // when
        emailTextFieldContent.value = "nadadhl1@"

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }


    @Test
    fun `비밀번호는_8에서_16자_사이어야_한다`(){
        // when
        passWordTextFieldContent.value = "nada"

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun `비밀번호에_영문_숫자_조합이_들어가지_않으면_에러메세지_노출된다`(){
        // when
        passWordTextFieldContent.value = "nadadhl@"

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }


    @Test
    fun `비밀번호_확인창에_작성한_비밀번호_값이_안들어가면_에러메세지_노출된다`(){
        // when
        passWordTextFieldContent.value = "nadadhl12"
        passWordConfirmTextFieldContent.value = "nadadhl1233"

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}