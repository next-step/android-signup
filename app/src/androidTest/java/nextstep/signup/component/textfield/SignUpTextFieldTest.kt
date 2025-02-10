package nextstep.signup.component.textfield

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import nextstep.signup.components.SignUpTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val text: MutableState<String> = mutableStateOf("")
    private val label = "username"

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = label,
                modifier = Modifier.testTag(TAG_SIGN_UP_TEXT_FIELD)
            )
        }
    }


    @Test
    fun 아무것도_입력_되지_않았을_때_라벨이_표시된다() {
        // when
        text.value = ""

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(label))
    }

    @Test
    fun 입력_되었을_때도_입력된_값과_라벨이_표시된다() {
        // given
        val inputtedValue = "김컴포즈"

        // when
        text.value = inputtedValue

        // then
        composeTestRule
            .onNodeWithTag(TAG_SIGN_UP_TEXT_FIELD)
            .assert(hasText(inputtedValue) and hasText(label))
    }

    companion object {
        private const val TAG_SIGN_UP_TEXT_FIELD = "SignUpTextField"
    }
}
