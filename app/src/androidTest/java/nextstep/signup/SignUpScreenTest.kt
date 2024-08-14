package nextstep.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.theme.screen.SignUp
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 입력을_하지않은_기본상태는_회원가입버튼이_비활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }
        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 사용자_이름_이메일_비밀번호_컨펌비밀번호를_정상적으로_입력하면_회원가입버튼이_활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("김컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("mioyo2@gmail.com")
        composeTestRule.onNodeWithText("Password").performTextInput("1q2w3e4r")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("1q2w3e4r")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 사용자_이름_필드_검증이_정상적이지_않으면_회원가입버튼이_비활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("김")
        composeTestRule.onNodeWithText("Email").performTextInput("mioyo2@gmail.com")
        composeTestRule.onNodeWithText("Password").performTextInput("1q2w3e4r")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("1q2w3e4r")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일_필드_검증이_정상적이지_않으면_회원가입버튼이_비활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("김컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("mioyo2gmail.com")
        composeTestRule.onNodeWithText("Password").performTextInput("1q2w3e4r")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("1q2w3e4r")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호_필드_검증이_정상적이지_않으면_회원가입버튼이_비활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("김컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("mioyo2gmail.com")
        composeTestRule.onNodeWithText("Password").performTextInput("1q2w3e4")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("1q2w3e4r")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호_확인_필드_검증이_정상적이지_않으면_회원가입버튼이_비활성화된다() {
        // when
        composeTestRule.setContent {
            SignUp()
        }

        composeTestRule.onNodeWithText("Username").performTextInput("김컴포즈")
        composeTestRule.onNodeWithText("Email").performTextInput("mioyo2gmail.com")
        composeTestRule.onNodeWithText("Password").performTextInput("1q2w3e4r")
        composeTestRule.onNodeWithText("Password Confirm").performTextInput("1q2w3e4")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
