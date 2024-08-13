package nextstep.signup.utils

import androidx.compose.ui.test.junit4.ComposeTestRule
import androidx.compose.ui.test.onNodeWithText

internal fun ComposeTestRule.assertExists(errorMessage: String) {
    onNodeWithText(errorMessage).assertExists()
}

internal fun ComposeTestRule.assertDoesNotExist(errorMessage: String) {
    onNodeWithText(errorMessage).assertDoesNotExist()
}
