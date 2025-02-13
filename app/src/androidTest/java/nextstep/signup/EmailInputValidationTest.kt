package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.userregister.widget.EmailInputField
import org.junit.Rule
import org.junit.Test

class EmailInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun `입력_값은_이메일_형식이어야_한다`() {
        // given
        val email = "fbghgus123@naver.com"

        // when
        composeTestRule.setContent {
            EmailInputField(
                value = email,
                onValueChange = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `입력_값이_이메일_형식이_아니라면_에러미세지가_노출된다`() {
        // given
        val email = "fbghgus123"

        // when
        composeTestRule.setContent {
            EmailInputField(
                value = email,
                onValueChange = {}
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }
}
