package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val enabled = mutableStateOf(true)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(text = "Sign Up", enabled = enabled.value, onClick = {})
        }
    }

    @Test
    fun enabled가_true면_버튼이_활성화된다() {
        enabled.value = true

        composeTestRule.onNodeWithText("Sign Up").assertIsEnabled()
    }

    @Test
    fun enabled가_false면_버튼이_비활성화된다() {
        enabled.value = false

        composeTestRule.onNodeWithText("Sign Up").assertIsNotEnabled()
    }
}