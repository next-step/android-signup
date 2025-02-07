package nextstep.signup

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.hasSetTextAction
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import androidx.compose.ui.text.input.KeyboardType
import nextstep.signup.ui.screen.InputField
import nextstep.signup.ui.screen.SignUpButton
import nextstep.signup.ui.screen.TitleText
import org.junit.Rule
import org.junit.Test


class SignUpViewTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun title() {
        /** given **/
        val text = "Welcome to Compose \uD83D\uDE80"

        composeTestRule.setContent {
            TitleText(
                titleResId = R.string.sign_up_title,
                modifier = Modifier.fillMaxWidth()
            )
        }

        /** then **/
        /** Text 일치 여부 테스트 **/
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }

    @Test
    fun input() {
        val testText = "진감자"
        val testPassword = "1234"

        /** given **/
        composeTestRule.setContent {
            var input by remember { mutableStateOf("") }

            InputField(
                labelResId = R.string.test_input,
                value = input,
                onValueChange = { input = it },
                modifier = Modifier.testTag("inputField")
            )

            InputField(
                labelResId = R.string.test_input_password,
                value = input,
                onValueChange = { input = it },
                inputType = KeyboardType.Password,
                modifier = Modifier.testTag("passwordField")
            )
        }

        /** then **/
        /** 값 입력 테스트 **/
        // 입력 필드에 텍스트 입력
        composeTestRule.onNodeWithTag("inputField")
            .performTextInput(testText)

        // 입력한 값이 정상적으로 반영되었는지 확인
        composeTestRule.onNodeWithTag("inputField")
            //	hasSetTextAction() → 입력 가능한 텍스트 (EditableText)만 포함
            //	hasText(testText) → 입력한 testText가 실제로 있는지 확인
            .assert(hasSetTextAction() and hasText(testText))

        /** 입력값 초기화 테스트 **/
        // 입력된 텍스트 삭제
        composeTestRule.onNodeWithTag("inputField")
            .performTextClearance()

        // 텍스트 필드가 비어 있는지 확인
        composeTestRule.onNodeWithTag("inputField")
            .assert(hasSetTextAction() and hasText(""))

        /** Password 입력 테스트 **/
        // 비밀번호 입력
        composeTestRule.onNodeWithTag("passwordField")
            .performTextInput(testPassword)

        // 입력값 존재 확인
        composeTestRule.onNodeWithTag("passwordField")
            .assert(hasSetTextAction())
    }

    @Test
    fun button() {
        /** given **/
        val buttonTag = "Sign Up"
        var clicked = false
        composeTestRule.setContent {
            SignUpButton(
                buttonTextResId = R.string.sign_up_button,
                modifier = Modifier.testTag(buttonTag),
            ) {
                clicked = true
            }
        }

        /** then **/

        /** 버튼 렌더링 여부 테스트 **/
        composeTestRule.onNodeWithText(buttonTag)
            .assertIsDisplayed()

        /** 버튼 활성화 여부 테스트 **/
        composeTestRule.onNodeWithTag(buttonTag)
            .assertIsEnabled()

        /** 버튼 클릭 테스트 **/
        // 버튼 클릭
        composeTestRule.onNodeWithTag(buttonTag)
            .performClick()

        // 클릭 이벤트가 실행되었는지 확인
        assert(clicked)
    }
}

