package nextstep.signup.component

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class TitleTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun titleText() {
        // given
        val text = "Welcome to Compose \uD83D\uDE80"
        composeTestRule.setContent {
            TitleText(text)
        }

        // then
        composeTestRule
            .onNodeWithText(text)
            .assertIsDisplayed()
    }

}
