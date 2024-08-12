package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.theme.screen.NameTextFieldScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class NameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "김컴포즈") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자보다_작으면_에러가노출() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "김") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름이_5자보다_크면_에러가노출() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "김컴포즈입니다") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_숫자나_기호가_포함될_수_없다() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "김컴포즈") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름에는_숫자가_포함되면_에러가_노출된다() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "1김컴포즈") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun 사용자_이름에는_기호가_포함되면_에러가_노출된다() {
        // when
        composeTestRule.setContent {
            NameTextFieldScreen("UserName",  "!김컴포즈") {
            }
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }
}
