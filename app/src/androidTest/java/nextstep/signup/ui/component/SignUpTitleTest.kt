package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertHeightIsAtLeast
import androidx.compose.ui.test.assertHeightIsEqualTo
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import org.junit.Rule
import org.junit.Test

class SignUpTitleTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private var title = "제목"

    @Test
    fun 제목_노출() {
        // given
        composeTestRule.setContent {
            SignUpTitle(text = title)
        }

        // then
        composeTestRule
            .onNodeWithText(title)
            .assertExists()
    }

    @Test
    fun 제목이_빈값이어도_텍스트영역_높이_유지() {
        // given
        val text = ""
        composeTestRule.setContent {
            SignUpTitle(
                text = title,
                modifier = Modifier.testTag(title)
            )
            SignUpTitle(text = text)
        }

        // then
        composeTestRule
            .onNodeWithText(text)
            .assertExists()
            .assertHeightIsAtLeast(20.dp)
    }

    @Test
    fun 제목이_길이가_길어도_한줄로_노출() {
        // given
        val longTitle =
            "긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다 긴 이름을 테스트 하기 위한 제목 입니다"
        composeTestRule.setContent {
            Column {
                SignUpTitle(
                    text = title,
                    modifier = Modifier.testTag(title)
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
            .assertHeightIsEqualTo(composeTestRule.heightDp(title))
    }

    private fun ComposeContentTestRule.heightDp(tag: String) = onNodeWithTag(tag)
        .fetchSemanticsNode()
        .size
        .height
        .div(composeTestRule.density.density)
        .dp
}