
package nextstep.signup.study

import androidx.compose.ui.test.assert
import androidx.compose.ui.test.assertCountEquals
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.hasTestTag
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onChildren
import androidx.compose.ui.test.onFirst
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import org.junit.Rule
import org.junit.Test

// 1. 모든 테스트가 성공하도록 만들어보자
// 2. 힌트를 참고하여 Preview를 노출시킨다.
// 3. Preview의 interactive 모드를 활용하여 버튼을 클릭해본다.
class LayoutBasicsTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun text() {
        // given
        composeTestRule.setContent {
            TextView(text = "안녕 난 깜뽀즈야")
        }

        // then
        composeTestRule
            .onNodeWithText("안녕 난 깜뽀즈야")
            .assertExists()
    }

    @Test
    fun column() {
        // given
        composeTestRule.setContent {
            ColumnView()
        }

        // then
        composeTestRule.onNode(hasTestTag("이름"))
            .onChildren()
            .assertCountEquals(3)
            .onFirst()
            .assert(hasText("깜포즈"))
    }

    @Test
    fun button() {
        // given
        composeTestRule.setContent {
            ButtonView()
        }

        // when
        val button = composeTestRule
            .onNode(hasTestTag("버튼"))
            .also { it.performClick() }

        // then
        button.assertIsNotEnabled()
    }
}
