package nextstep.signup.component.button

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.InputFields
import nextstep.signup.SignUpScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        // given
        composeTestRule.setContent {
            val inputFields: InputFields = remember { InputFields() }

            SignUpScreen(
                inputFields = inputFields,
                onSignUpButtonClick = {},
                modifier = Modifier.fillMaxSize()
            )
        }
    }

    @Test
    fun 가입_버튼의_초기상태는_비활성화다() {
        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }

    @Test
    fun 모든_입력폼을_유효하게_입력하면_가입_버튼이_활성화_된다() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("문상현")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("no1msh1217@gmail.com")
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("asdf1234")
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("asdf1234")

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 하나의_입력폼이라도_유효하지_않다면_가입_버튼은_비활성화다() {
        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput("문상현")

        composeTestRule
            .onNodeWithText("Email")
            .performTextInput("no1msh1217@gmail.com")
        composeTestRule
            .onNodeWithText("Password")
            .performTextInput("asdf1234")
        composeTestRule
            .onNodeWithText("Password Confirm")
            .performTextInput("asdf123") // 비밀번호와 일치하지 않음

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
