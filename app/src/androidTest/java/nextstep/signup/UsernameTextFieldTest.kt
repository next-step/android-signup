package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.view.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            UsernameTextField()
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // given

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("김컴포즈다")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("김컴포즈이다")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 사용자_이름이_한글자면_에러메시지가_노출된다() {

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("김")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_LENGTH_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_숫자가_포함되면_에러메시지가_노출된다() {

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("James1")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_ERROR)
            .assertExists()
    }

    @Test
    fun 이름에_기호가_포함되면_에러메시지가_노출된다() {

        // when
        composeTestRule.onNodeWithText(USERNAME).performTextInput("James!")

        // then
        composeTestRule
            .onNodeWithText(USERNAME_INVALID_ERROR)
            .assertExists()
    }

    companion object {
        private const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        private const val USERNAME_INVALID_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
        private const val USERNAME = "Username"
    }
}