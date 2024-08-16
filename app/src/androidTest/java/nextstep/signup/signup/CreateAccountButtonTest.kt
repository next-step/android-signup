package nextstep.signup.signup

import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.signup.component.CreateAccountButton
import org.junit.Rule
import org.junit.Test

class CreateAccountButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun isSuccessful이_true이면_버튼이_활성화된다() {
        // given:
        val isSuccessful = true

        // when:
        composeTestRule.apply {
            setContent {
                CreateAccountButton(
                    isSuccessfulCondition = isSuccessful,
                    onCreateAccountButtonClick = {},
                )
            }
        }

        // then:
        composeTestRule.onNodeWithText("Sign Up").assertIsEnabled()
    }
}
