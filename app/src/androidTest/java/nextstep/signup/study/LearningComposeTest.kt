package nextstep.signup.study

import androidx.compose.material3.Text
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class LearningComposeTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        composeTestRule.setContent {
            Text(text = "텍스트")
        }

        composeTestRule
            .onNodeWithText("텍스트")
            .assertExists()
    }

}