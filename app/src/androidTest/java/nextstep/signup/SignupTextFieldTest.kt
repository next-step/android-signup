package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import junit.framework.TestCase.assertEquals
import nextstep.signup.ui.SignupTextField
import org.junit.Rule
import org.junit.Test


class SignupTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun updatesValueOnInput() {
        // Given
        val useNameLabel = "Username"
        val expectedValue = "test"
        var actualValue = ""

        composeTestRule.setContent {
            SignupTextField(
                label = useNameLabel,
                value = actualValue,
                onValueChange = { actualValue = it }
            )
        }

        // Then
        composeTestRule
            .onNodeWithText(useNameLabel)
            .performTextInput(expectedValue)

        assertEquals(expectedValue, actualValue)
    }
}