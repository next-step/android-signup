package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.component.SignUpTitle
import org.junit.Rule
import org.junit.Test

class SignUpTitleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun showTitle() {
        // given
        val text = "만나서 반가워"
        composeTestRule.setContent {
            SignUpTitle(text)
        }

        // then
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
    }

    @Test
    fun emptyTitle() {
        // given
        val text = ""
        composeTestRule.setContent {
            SignUpTitle(text)
        }

        // then
        composeTestRule
            .onNodeWithText(text)
            .assertHeightIsEqualTo(0.dp)
    }

    @Test
    fun longTitle() {
        // given
        val shortTitle = "짧은 제목"
        val longTitle =
            "긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다"
        composeTestRule.setContent {
            Column {
                SignUpTitle(
                    text = shortTitle,
                    modifier = Modifier.testTag(shortTitle)
                )
                SignUpTitle(
                    text = longTitle,
                    modifier = Modifier.testTag(longTitle)
                )
            }
        }

        // then
        composeTestRule
            .onNodeWithTag(longTitle)
            .assertHeightIsEqualTo(composeTestRule.heightDp(shortTitle))
    }

    private fun ComposeContentTestRule.heightDp(tag: String) = onNodeWithTag(tag)
        .fetchSemanticsNode()
        .size
        .height
        .div(composeTestRule.density.density)
        .dp
}