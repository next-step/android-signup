package nextstep.signup.ui.component

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showButton() {
        val tag = "show"
        // given
        composeTestRule.setContent {
            SignUpButton(
                modifier = Modifier
                    .testTag(tag),
                enabled = true,
                onClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assertExists()
            .assertIsEnabled()
    }

    @Test
    fun disableCheck() {
        val tag = "disable"
        // given
        composeTestRule.setContent {
            SignUpButton(
                modifier = Modifier
                    .testTag(tag),
                enabled = false,
                onClick = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(tag)
            .assertIsNotEnabled()
    }
}