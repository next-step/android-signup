package nextstep.signup.signup.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.InputEmail
import org.junit.Rule
import org.junit.Test

class EmailInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이메일_형식이_올바르지_않으면_에러_문구를_표시한다() {
        // given when
        composeTestRule.setContent {
            EmailInputField(
                inputEmail = InputEmail("gmail"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르면_에러_문구를_표시하지_않는다() {
        // given when
        composeTestRule.setContent {
            EmailInputField(
                inputEmail = InputEmail("mome@channel.io"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertDoesNotExist()
    }
}
