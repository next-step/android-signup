package nextstep.signup.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.text.input.VisualTransformation
import org.junit.Rule
import org.junit.Test

class SignupTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `컴포넌트가_렌더링_된다`() {
        // given
        val label = "UserName"
        composeTestRule.setContent {
            SignupTextField(
                label = label,
                text = "text",
                onValueChange = { },
                visualTransformation = VisualTransformation.None,
                modifier = Modifier.testTag(label)
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(label)
            .assertExists()
    }
}
