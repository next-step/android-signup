package nextstep.signup

import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class MainScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showMainScreen() {
        val tag = "mainScreen"
        composeTestRule.setContent {
            MainScreen(
                onShowSnackBar = {},
                modifier = Modifier.testTag(tag),
            )
        }

        composeTestRule
            .onNodeWithTag(tag)
            .onChildren()
            .assertCountEquals(6)
    }
}