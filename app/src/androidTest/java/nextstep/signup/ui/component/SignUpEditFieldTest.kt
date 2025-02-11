package nextstep.signup.ui.component

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildAt
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.model.SignUpInputModel
import org.junit.Rule
import org.junit.Test

class SignUpEditFieldTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 사용자_입력창에_사용자명_이메일_비밀번호_비밀번호확인_노출() {

        // given
        val tag = "tag"
        composeTestRule.setContent {
            val model by remember { mutableStateOf(SignUpInputModel()) }
            SignUpEditFields(
                inputModel = model,
                modifier = Modifier.testTag(tag),
                onUpdateModel = {}
            )
        }

        // then
        composeTestRule.onNodeWithTag(tag)
            .onChildren()
            .assertCountEquals(4)
    }

    @Test
    fun 사용자_입력창_값_입력_시_업데이트() {
        // given
        val tag = "tag"
        val input = "input"
        composeTestRule.setContent {
            var model by remember { mutableStateOf(SignUpInputModel()) }
            SignUpEditFields(
                inputModel = model,
                modifier = Modifier.testTag(tag),
                onUpdateModel = { model = it }
            )
        }

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .onChildAt(0)
            .performTextInput(input)

        // then
        composeTestRule.onNodeWithTag(tag)
            .onChildAt(0)
            .assert(hasText(input))
    }

    @Test
    fun 사용자_입력값이_유효하지_않다면_에러_문구_노출() {
        // given
        val tag = "tag"
        val username = "a"
        composeTestRule.setContent {
            var model by remember { mutableStateOf(SignUpInputModel()) }
            SignUpEditFields(
                inputModel = model,
                modifier = Modifier.testTag(tag),
                onUpdateModel = { model = it }
            )
        }

        // when
        composeTestRule
            .onNodeWithTag(tag)
            .onChildAt(0)
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }
}