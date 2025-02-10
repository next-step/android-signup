package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.userregister.widget.UsernameInputField
import nextstep.signup.util.ValidationUtil.setUsernameErrorMessage
import org.junit.Rule
import org.junit.Test

class UsernameInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    private fun setUsernameInputField(userName: String) {
        composeTestRule.setContent {
            UsernameInputField(
                value = userName,
                errorMessage = setUsernameErrorMessage(userName),
                onValueChange = {}
            )
        }
    }

    @Test
    fun `사용자_이름은_2에서_5자여야_한다`() {
        // given
        val userName = "김컴포즈"

        // when
        setUsernameInputField(userName)

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // given
        val userName = "김컴포즈입니다"

        // when
        setUsernameInputField(userName)

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun `사용자_이름에_특수문자가_없어야_한다`() {
        // given
        val userName = "김컴포즈"

        // when
        setUsernameInputField(userName)

        // then
        composeTestRule
            .onNodeWithText("이름에 특수문자가 들어갈 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun `사용자_이름에_특수문자가_있으면_에러메세지가_노출된다`() {
        // given
        val userName = "김컴포즈!"

        // when
        setUsernameInputField(userName)

        // then
        composeTestRule
            .onNodeWithText("이름에 특수문자가 들어갈 수 없습니다.")
            .assertExists()
    }
}
