package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.text.input.KeyboardType
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val inputData = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            TextFieldView("UserName", keyboardType = KeyboardType.Text, inputString = inputData)
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
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
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

//    @Test
//    fun 이메일_형식이_올바르지_않은경우_에러메시지가_노출된다() {
//        // when
//        username.value = "김컴포즈입니다"
//
//        // then
//        composeTestRule
//            .onNodeWithText(USERNAME_LENGTH_ERROR)
//            .assertExists()
//    }
//
//    @Test
//    fun 비밀번호는_8에서_16자여야_한다() {
//        // when
//        username.value = "김컴포즈입니다"
//
//        // then
//        composeTestRule
//            .onNodeWithText(USERNAME_LENGTH_ERROR)
//            .assertExists()
//    }
//
//    @Test
//    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
//        // when
//        username.value = "김컴포즈입니다"
//
//        // then
//        composeTestRule
//            .onNodeWithText(USERNAME_LENGTH_ERROR)
//            .assertExists()
//    }
//
//    @Test
//    fun 비밀번호가_확인비밀번호는_일치해야_한다() {
//        // when
//        username.value = "김컴포즈입니다"
//
//        // then
//        composeTestRule
//            .onNodeWithText(USERNAME_LENGTH_ERROR)
//            .assertExists()
//    }


    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_REX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}
