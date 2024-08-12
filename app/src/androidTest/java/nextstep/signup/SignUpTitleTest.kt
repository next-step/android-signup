package nextstep.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.view.SignUpTitle
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpTitleTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    fun setUp() {
        composeTestRule.setContent {
            SignUpTitle()
        }
    }

    @Test
    fun 타이틀이_표시되어야_한다() {

        // then
        composeTestRule
            .onNodeWithText(SIGN_UP_TITLE)
            .assertExists()
    }

    companion object {
        private const val SIGN_UP_TITLE = "Welcome to Compose \uD83D\uDE80"
    }
}