package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Rule
import org.junit.Test

class TextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun NSTextField의_값을_입력하면_표시되어야한다() {
        // given
        val value = "ABC"
        val text = mutableStateOf(value)

        composeTestRule.setContent {
            BaseTextField(value = text.value, onValueChange = {}, labelValue = "")
        }

        // then
        composeTestRule
            .onNodeWithTag(NS_TEXT_FIELD_TAG)
            .assertExists()
            .assert(hasText(value))
    }

    @Test
    fun NSTextField의_supportingTextValue값을_입력하면_표시되어야한다() {
        // given
        val value = "ABC"
        composeTestRule.setContent {
            BaseTextField(
                value = "",
                onValueChange = {},
                labelValue = "",
                supportingTextValue = value,
            )
        }

        // then
        composeTestRule
            .onNodeWithTag(NS_TEXT_FIELD_TAG)
            .assertExists()
            .assert(hasText(value))
    }

    companion object {
        private const val NS_TEXT_FIELD_TAG = "nsTextField"
    }

}