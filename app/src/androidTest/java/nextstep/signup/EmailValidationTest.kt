package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.screen.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val email = mutableStateOf<String>("")


    @Before
    fun setUp() {
        composeTestRule.setContent {
            EmailTextField(
                email = email.value,
                onEmailChange = { email.value = it },
                modifier = Modifier.testTag(EMAIL_TEST_TAG)
            )
        }
    }

    @Test
    fun 이메일_형식이_올바라야_한다() {
        // when
        val email = "test@test.net"

        // then
        composeTestRule.onNodeWithTag(EMAIL_TEST_TAG)
            .performTextInput(email)

        composeTestRule.onNodeWithTag(EMAIL_TEST_TAG)
            .assert(!hasText(EMAIL_REGEX_ERROR))
    }

    @Test
    fun 이메일_형식이_올바르지_않은_경우_오류메시지가_노출된다() {
        // when
        val email = "test@net"

        // then
        composeTestRule.onNodeWithTag(EMAIL_TEST_TAG)
            .performTextInput(email)

        composeTestRule.onNodeWithTag(EMAIL_TEST_TAG)
            .assert(hasText(EMAIL_REGEX_ERROR))
    }

    companion object {
        const val EMAIL_TEST_TAG = "Email"
        const val EMAIL_REGEX_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}

