package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.signin.SignupScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

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

    companion object {
        // Label
        private const val USERNAME_LABEL = "Username"

        // ErrorMessage
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_FORMAT_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}