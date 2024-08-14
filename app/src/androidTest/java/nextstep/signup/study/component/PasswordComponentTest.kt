package nextstep.signup.study.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.component.PasswordComponent
import org.junit.Rule
import org.junit.Test

class PasswordComponentTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호가_8에서_16자이고_영문과_숫자를_포함하면_에러메시지가_노출되지_않는다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(password = "password123",
                passwordConfirm = "",
                onPasswordChange = {},
                onPasswordConfirmChange = {})
        }
        // Then
        composeTestRule.onNodeWithText("비밀번호는 8~16자여야 합니다.").assertDoesNotExist()
        composeTestRule.onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.").assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8자_미만이면_에러메시지가_노출된다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(password = "pass1",
                passwordConfirm = "",
                onPasswordChange = {},
                onPasswordConfirmChange = {}
            )
        }
        // Then
        composeTestRule.onNodeWithText("비밀번호는 8~16자여야 합니다.").assertExists()
    }

    @Test
    fun 비밀번호가_16자_초과면_에러메시지가_노출된다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(password = "password123password123password123",
                passwordConfirm = "",
                onPasswordChange = {},
                onPasswordConfirmChange = {})
        }

        // Then
        composeTestRule.onNodeWithText("비밀번호는 8~16자여야 합니다.").assertExists()
    }

    @Test
    fun 비밀번호에_영문과_숫자가_포함되지_않으면_에러메시지가_노출된다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(password = "password",
                passwordConfirm = "",
                onPasswordChange = {},
                onPasswordConfirmChange = {})
        }

        // Then
        composeTestRule.onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.").assertExists()
    }


    @Test
    fun 비밀번호와_비밀번호_확인이_일치하면_에러메시지가_노출되지_않는다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(
                password = "password123",
                passwordConfirm = "password123",
                onPasswordChange = {},
                onPasswordConfirmChange = {})
        }

        // Then
        composeTestRule.onNodeWithText("비밀번호가 일치하지 않습니다.").assertDoesNotExist()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않으면_에러메시지가_노출된다() {
        // When
        composeTestRule.setContent {
            PasswordComponent(password = "password123",
                passwordConfirm = "password124",
                onPasswordChange = {},
                onPasswordConfirmChange = {})
        }
        // Then
        composeTestRule.onNodeWithText("비밀번호가 일치하지 않습니다.").assertExists()
    }
}