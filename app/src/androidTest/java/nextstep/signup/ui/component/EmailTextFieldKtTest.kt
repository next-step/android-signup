package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextFieldKtTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                email = email.value,
                onValueChange = { value ->
                    email.value = value
                }
            )
        }
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // given

        // when
        email.value = "1234@#test.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_FORMAT_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}
