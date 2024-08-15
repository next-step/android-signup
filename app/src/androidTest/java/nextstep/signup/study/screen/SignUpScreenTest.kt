package nextstep.signup.study.screen

import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.SemanticsProperties
import androidx.compose.ui.test.SemanticsMatcher
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import nextstep.signup.SignUpScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()


    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpScreen()
        }
    }

    private fun signUpButtonMatcher(): SemanticsMatcher {
        return SemanticsMatcher.expectValue(
            SemanticsProperties.Role,
            Role.Button
        ) and hasText("Sign Up")
    }


    @Test
    fun signUpButton_초기에는_비활성화되어있다() {
        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 모든필드가_유효하면_signUpButton이_활성화된다() {
        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("compose@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsEnabled()
    }

    @Test
    fun 사용자이름이_비어있으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 사용자이름이_유효하지않으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Username").performTextInput("c") // 2자 미만
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 이메일이_비어있으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Username").performTextInput("compose")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 이메일이_유효하지않으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Username").performTextInput("John")
        composeTestRule.onNodeWithText("Email").performTextInput("invalid-email")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_비어있으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_유효하지않으면_signUpButton이_비활성화된다() {

        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("pass")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("pass")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 비밀번호확인이_비어있으면_signUpButton이_비활성화된다() {
        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun 비밀번호와_비밀번호확인이_일치하지않으면_signUpButton이_비활성화된다() {
        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password456")

        composeTestRule.onNode(signUpButtonMatcher()).assertIsNotEnabled()
    }

    @Test
    fun signUp버튼_클릭시_성공_Snackbar가_표시된다() {
        composeTestRule.onNodeWithText("Username").performTextInput("컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("test@test.com")
        composeTestRule.onNodeWithText("Password").performTextInput("password123")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("password123")

        composeTestRule.onNode(signUpButtonMatcher()).performClick()

        composeTestRule.waitUntil(3000) {
            composeTestRule.onAllNodesWithText("회원 가입 완료")
                .fetchSemanticsNodes()
                .isNotEmpty()
        }

        composeTestRule.onNodeWithText("회원 가입 완료").assertExists()
    }

}