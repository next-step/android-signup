package nextstep.signup.signup.component

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.InputUsername
import org.junit.Rule
import org.junit.Test

class UsernameInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun username의_길이가_2이상_5이하이면_에러메시지를_표시하지_않는다() {
        // given when
        composeTestRule.setContent {
            UsernameInputField(
                inputUsername = InputUsername("마리빈씨"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun username의_길이가_2미만이면_에러메시지를_표시한다() {
        // given when
        composeTestRule.setContent {
            UsernameInputField(
                inputUsername = InputUsername("말"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun username의_길이가_5초과이면_에러메시지를_표시한다() {
        // given when
        composeTestRule.setContent {
            UsernameInputField(
                inputUsername = InputUsername("malibin"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun username은_영문_또는_한글만_입력할_수_있다() {
        // given when
        composeTestRule.setContent {
            UsernameInputField(
                inputUsername = InputUsername("말li빈"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun username이_숫자_혹은_기호를_포함하면_에러_문구를_표시한다() {
        // given when
        composeTestRule.setContent {
            UsernameInputField(
                inputUsername = InputUsername("말#1"),
                onInputChanged = {},
            )
        }

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }
}
