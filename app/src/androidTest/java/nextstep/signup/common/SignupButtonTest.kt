package nextstep.signup.common

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import nextstep.signup.ui.common.component.SignupButton
import org.junit.Rule
import org.junit.Test

class SignupButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val signUpButtonTag = "SignupButtonTag"

    @Test
    fun 버튼의_텍스트를_지정할_수_있다() {
        // given:
        val givenButtonText = "지정된 버튼 텍스트"

        // when:
        composeTestRule.apply {
            setContent {
                SignupButton(
                    buttonText = givenButtonText,
                    buttonTextColor = Color.Unspecified,
                    onButtonClick = {},
                    containerColor = Color.Unspecified,
                    enabled = true,
                    componentDescription = signUpButtonTag
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(signUpButtonTag)
            .assertTextEquals("지정된 버튼 텍스트")
    }

    @Test
    fun 버튼의_활성화를_지정할_수_있다() {
        // given:
        val givenEnabled = false

        // when:
        composeTestRule.apply {
            setContent {
                SignupButton(
                    buttonText = "buttonText",
                    buttonTextColor = Color.Unspecified,
                    onButtonClick = {},
                    containerColor = Color.Unspecified,
                    enabled = givenEnabled,
                    componentDescription = signUpButtonTag
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(signUpButtonTag).assertIsNotEnabled()
    }
}
