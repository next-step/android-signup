package nextstep.signup.common

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithContentDescription
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.common.component.SignupTextField
import org.junit.Rule
import org.junit.Test

class SignupTextFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val signUpTextFieldTag = "SignUpTextFieldTag"

    @Test
    fun 텍스트필드의_라벨을_지정할_수_있다() {
        // given:
        val givenTextFieldLabel = "지정된 버튼 라벨"

        // when:
        composeTestRule.apply {
            setContent {
                SignupTextField(
                    text = "",
                    onTextChanged = {},
                    label = givenTextFieldLabel,
                    componentDescription = signUpTextFieldTag
                )
            }
        }

        // then:
        composeTestRule.onNodeWithContentDescription(signUpTextFieldTag)
            .assertTextContains("지정된 버튼 라벨")
    }

    @Test
    fun 입력한_텍스트가_텍스트_필드에_출력된다() {
        // given:
        var text by mutableStateOf("")
        val givenText = "테스트"

        // when:
        composeTestRule.apply {
            setContent {
                SignupTextField(
                    text = text,
                    onTextChanged = { text = it },
                    label = "",
                    componentDescription = signUpTextFieldTag
                )
            }

            onNodeWithContentDescription(signUpTextFieldTag).performTextInput(givenText)
        }


        // then:
        composeTestRule.onNodeWithContentDescription(signUpTextFieldTag)
            .assertTextContains("테스트")
    }
}
