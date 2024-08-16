package nextstep.signup.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.component.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            UsernameTextField(
                value = username.value,
                onValueChange = {},
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
    fun 사용자_이름이_5자초과하면_에러메시지가_노출된다() {
        // when
        username.value = "컴포즈입니다"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_2자미만이면_에러메시지가_노출된다() {
        // when
        username.value = "김"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름은_숫자나_기호가_포함되지_않아야한다() {
        // when
        username.value = "포즈"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REGEX_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에_숫자가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈1"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름에_기호가_포함되면_에러메시지가_노출된다() {
        // when
        username.value = "김컴포즈@"

        // then
        composeTestRule
            .onNodeWithText(USERNAME_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_비어있으면_에러가_표시되지_않는다() {
        // when
        username.value = ""

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText(USERNAME_REGEX_ERROR)
            .assertDoesNotExist()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_REGEX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }

}