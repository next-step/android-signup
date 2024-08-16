package nextstep.signup.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.ui.signup.component.HeaderText
import org.junit.Rule
import org.junit.Test

class HeaderTextTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 회원가입_뷰_헤더가_출력된다() {
        // when:
        composeTestRule.apply {
            setContent {
                HeaderText()
            }
        }

        // then:
        composeTestRule.onNodeWithText("Welcome to Compose \uD83D\uDE80")
            .assertExists()
    }
}
