package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.validation.InputValidation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    private lateinit var userNameValidation: InputValidation.UserNameValidation
    private lateinit var invalidMsg: String
    private lateinit var invalidLengthMsg: String

    @Before
    fun setup() {
        composeTestRule.setContent {
            invalidMsg = stringResource(R.string.user_name_invalid_msg)
            invalidLengthMsg = stringResource(R.string.user_name_invalid_length_msg)
            userNameValidation = InputValidation.UserNameValidation(
                invalidMsg,
                invalidLengthMsg
            )

            SignUpTextFieldComponent(
                labelText = "userName",
                {  userNameValidation.checkValidation(username.value) }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(invalidLengthMsg)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(invalidLengthMsg)
            .assertExists()

    }

    @Test
    fun 사용자_이름에_특수문자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김!컴포즈"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "2컴포즈"

        // then
        composeTestRule
            .onNodeWithText(invalidMsg)
            .assertExists()

    }
}
