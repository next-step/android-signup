package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.model.SignUpInputModel
import org.junit.Rule
import org.junit.Test

class SignUpEditFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showSignUpEditField() {

        // given
        val tag = "tag"
        composeTestRule.setContent {
            val model by remember { mutableStateOf(SignUpInputModel()) }
            SignUpEditFields(
                inputModel = model,
                modifier = Modifier.testTag(tag),
                onUpdateModel = {}
            )
        }

        // then
        composeTestRule.onNodeWithTag(tag)
            .onChildren()
            .assertCountEquals(4)
    }


    @Test
    fun userInputChanged() {
        // given
        val tag = "tag"
        val input = "input"
        composeTestRule.setContent {
            var model by remember { mutableStateOf(SignUpInputModel()) }
            SignUpEditFields(
                inputModel = model,
                modifier = Modifier.testTag(tag),
                onUpdateModel = { model = it }
            )
        }

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .onChildAt(0)
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithTag(tag)
            .onChildAt(0)
            .assert(hasText(input))
    }
}