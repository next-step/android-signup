package nextstep.signup.ui.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.signup.MainScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            MainScreen()
        }
    }

    @Test
    fun 모든_입력폼을_유효하게_입력_후_가입_버튼을_누르면_스낵바가_보여진다() {
        // when
        val username = "name"
        val email = "user@domain.com"
        val passwordInput = "password1"
        val passwordConfirm = "password1"

        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput(email)

        composeTestRule
            .onNodeWithText("Password")
            .performTextInput(passwordInput)

        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput(passwordConfirm)

        composeTestRule
            .onNodeWithText("Sign up")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("회원가입 완료", useUnmergedTree = true)
            .assertIsDisplayed()
    }
}