package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.util.ValidationUtil.EMAIL_FORMAT_ERROR
import nextstep.signup.util.ValidationUtil.setEmailErrorMessage
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailInputField(
                value = email.value,
                errorMessage = setEmailErrorMessage(email.value),
                onValueChange = { email.value = it }
            )
        }
    }

    @Test
    fun `입력_값은_이메일_형식이어야_한다`() {
        // when
        email.value = "fbghgus123@naver.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun `입력_값이_이메일_형식이_아니라면_에러미세지가_노출된다`() {
        // when
        email.value = "fbghgus123"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_FORMAT_ERROR)
            .assertExists()
    }
}
