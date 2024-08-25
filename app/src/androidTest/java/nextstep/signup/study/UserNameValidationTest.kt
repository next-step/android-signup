package nextstep.signup.study

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.SignUpTextFieldComponent
import nextstep.signup.validation.InputValidation
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserNameValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val inputValidation = InputValidation.UserNameValidation(
        USERNAME_VALID_ERROR,
        USERNAME_LENGTH_ERROR
    )

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextFieldComponent(
                labelText = "userName",
                {  inputValidation.checkValidation(username.value) }
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        username.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()

    }

    @Test
    fun 사용자_이름에_특수문자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김!컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_VALID_ERROR)
            .assertExists()

    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "2컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_VALID_ERROR)
            .assertExists()

    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_VALID_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
