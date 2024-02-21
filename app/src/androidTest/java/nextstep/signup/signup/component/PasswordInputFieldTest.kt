package nextstep.signup.signup.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.InputPassword
import org.junit.Rule
import org.junit.Test

class PasswordInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 비밀번호의_길이는_8이상_16이하_이다() {
        // given when
        composeTestRule.setContent {
            PasswordInputField(
                inputPassword = InputPassword("1234567a"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호의_길이가_8미만이면_에러_문구를_표시한다() {
        // given when
        composeTestRule.setContent {
            PasswordInputField(
                inputPassword = InputPassword("12"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호의_길이가_16초과이면_에러_문구를_표시한다() {
        // given when
        composeTestRule.setContent {
            PasswordInputField(
                inputPassword = InputPassword("a12345678901234567890"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_모두_포함하지_않으면_에러_문구를_표시한다() {
        // when
        composeTestRule.setContent {
            PasswordInputField(
                inputPassword = InputPassword("1234567890"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }
}
