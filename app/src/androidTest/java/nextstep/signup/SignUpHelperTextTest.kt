package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.view.SignUpHelperText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpHelperTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val message = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpHelperText(message = message.value)
        }
    }

    @Test
    fun 메시지가_비어있으면_표시되지_않아야_한다() {
        composeTestRule.onNodeWithText("").assertDoesNotExist()
    }

    @Test
    fun 메시지가_있으면_표시되어야_한다() {

        // when
        message.value = "테스트 메시지입니다."

        // then
        composeTestRule.onNodeWithText("테스트 메시지입니다.").assertExists()
    }

}