package nextstep.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.theme.screen.SignUpButton
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setup() {
    }

    @Test
    fun 버튼이_활성화인_경우_사용가능한_상태이다() {
        // when
        composeTestRule.setContent {
            SignUpButton(enabled = true) {

            }
        }

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsEnabled()
    }

    @Test
    fun 버튼이_비활성화인_경우_사용불가능한_상태이다() {
        // when
        composeTestRule.setContent {
            SignUpButton(enabled = false) {

            }
        }

        // then
        composeTestRule
            .onNodeWithText("Sign Up")
            .assertIsNotEnabled()
    }
}
