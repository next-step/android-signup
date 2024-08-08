package nextstep.signup.study.component

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.screen.SignUpTextField
import org.junit.Rule
import org.junit.Test

class SignUpTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val emptyText = ""
    private val userName = "lee-ji-hoon"
    private val labelText = "username-label"
    private val labelTag = "username-label-tag"

    /**
     * given
     *  - `emptyText` 제공
     *  - `labelText` 제공
     *
     * then
     *  - `SignUpTextField`에 text가 비어있어도 label은 존재해야 한다.
     */
    @Test
    fun signUpEmptyTextField() {
        composeTestRule.setContent {
            SignUpTextField(
                text = emptyText,
                onValueChange = { },
                label = {
                    Text("labelText", modifier = Modifier.testTag(labelTag))
                }
            )
        }

        composeTestRule
            .onNodeWithTag(labelTag, useUnmergedTree = true)
            .assertExists()
    }

    /**
     * given
     *  - `userName` 제공
     *  - `labelText` 제공
     *
     * then
     *  - `SignUpTextField`에 text가 존재해도 label이 존재해야 한다.
     */
    @Test
    fun signUpTextField() {
        composeTestRule.setContent {
            SignUpTextField(
                text = userName,
                label = {
                    Text(
                        modifier = Modifier.testTag(labelTag),
                        text = labelText
                    )
                },
                onValueChange = {}
            )
        }

        composeTestRule
            .onNodeWithTag(labelTag, useUnmergedTree = true)
            .assertExists()
    }
}