package nextstep.signup.study.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.component.textField.UserNameTextField
import nextstep.signup.model.UserNameError
import nextstep.signup.model.UserNameState
import org.junit.Rule
import org.junit.Test

class UserNameTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 사용자_이름이_2에서_5자면_에러메시지가_노출되지_않는다() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = "컴포즈", onUserNameChange = {}, onValidationResult = {}

            )
        }

        composeTestRule.onNodeWithText("이름은 2~5자여야 합니다.").assertDoesNotExist()
        composeTestRule.onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.").assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2자_미만이면_에러메시지가_노출된다() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = "안",
                onUserNameChange = {},
                onValidationResult = {}

            )
        }

        composeTestRule.onNodeWithText("이름은 2~5자여야 합니다.").assertExists()
    }

    @Test
    fun 사용자_이름이_5자_초과면_에러메시지가_노출된다() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = "컴포즈컴포즈",
                onUserNameChange = {},
                onValidationResult = {}
            )
        }

        composeTestRule.onNodeWithText("이름은 2~5자여야 합니다.").assertExists()
    }

    @Test
    fun 사용자_이름에_숫자나_기호가_포함되면_에러메시지가_노출된다() {
        composeTestRule.setContent {
            UserNameTextField(
                userName = "컴포즈1",
                onUserNameChange = {},
                onValidationResult = {}
            )
        }

        composeTestRule.onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.").assertExists()
    }
}