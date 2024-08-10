package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.ui.theme.screen.TextFieldView
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UserInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val inputData = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            TextFieldView("UserName", keyboardType = KeyboardType.Text, inputState = inputData)
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        inputData.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자보다_작으면_에러가노출() {
        // when
        inputData.value = "김"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_5자보다_크면_에러가노출() {
        // when
        inputData.value = "김컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함될_수_없다() {
        // when
        inputData.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REX_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에는_숫자가_포함되면_에러가_노출된다() {
        // when
        inputData.value = "1김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REX_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_기호가_포함되면_에러가_노출된다() {
        // when
        inputData.value = "!김컴포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REX_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_REX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
