package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.core.validation.NameValidator
import org.junit.Rule
import org.junit.Test

class ValidatedTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private val emptyText = ""
    private val userName = "이지훈"
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
            ValidatedTextField(
                InputFieldModel(
                    value = emptyText,
                    onValueChange = {},
                    validator = NameValidator(),
                    label = {
                        Text("labelText", modifier = Modifier.testTag(labelTag))
                    }
                )
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
            ValidatedTextField(
                InputFieldModel(
                    value = userName,
                    onValueChange = {},
                    validator = NameValidator(),
                    label = {
                        Text(
                            modifier = Modifier.testTag(labelTag),
                            text = labelText
                        )
                    },
                )
            )
        }

        composeTestRule
            .onNodeWithTag(labelTag, useUnmergedTree = true)
            .assertExists()
    }
}