package nextstep.signup.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

class SignupButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `버튼의_클릭이벤트가_동작한다`() {
        // given
        composeTestRule.setContent {
            var text by remember { mutableStateOf("Sign Up") }
            SignupButton(
                text = text,
                onClick = { text = "Done" }
            )
        }

        // when
        composeTestRule
            .onNodeWithText("Sign Up")
            .performClick()

        // then
        composeTestRule
            .onNodeWithText("Done")
            .assertExists()
    }
}
