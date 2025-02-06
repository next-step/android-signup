package nextstep.signup.component

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.components.SignUpTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpTextFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val text: MutableState<String> = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = "username",
            )
        }
    }


    @Test
    fun 아무것도_입력_되지_않았을_때_라벨이_표시된다() {
        // when
        text.value = ""

        // then
        composeTestRule
            .onNodeWithText("username")
            .assertExists()
    }

    @Test
    fun 입력_되었을_때도_계속_라벨이_표시된다() {
        // when
        text.value = "김컴포즈"

        // then
        composeTestRule
            .onNodeWithText("username")
            .assertExists()
    }

}
