package nextstep.signup.ui.component.inputField

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            EmailInputField(
                email = email.value,
                onValueChange = { email.value = it },
                onValidationSuccess = {}
            )
        }
    }

    @Test
    fun 이메일은_형식에_맞아야_한다() {
        // when
        email.value = "sjjeong1225@gmail.com"

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_형식에_맞지_않으면_에러메시지가_노출된다() {
        // when
        email.value = "sjjeong1225.gmail.com"

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

}
