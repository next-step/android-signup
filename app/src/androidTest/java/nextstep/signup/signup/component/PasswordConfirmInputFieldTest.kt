package nextstep.signup.signup.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class PasswordConfirmInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호_확인_입력값은_비밀번호_입력값과_같아야_한다() {
        // given when
        composeTestRule.setContent {
            PasswordConfirmInputField(
                value = "00000000a",
                passwordToCompare = "00000000a",
                onTextChanged = {},
                onPasswordMatched = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인_입력값이_비밀번호_입력값과_다르면_에러_문구를_표시한다() {
        // given when
        composeTestRule.setContent {
            PasswordConfirmInputField(
                value = "00000000BB",
                passwordToCompare = "00000000a",
                onTextChanged = {},
                onPasswordMatched = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}
