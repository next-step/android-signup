package nextstep.signup

import android.content.Context
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performTextInput
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Rule
import org.junit.Test

class SignUpScreenUsernameTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun 이름을_입력하면_입력된_이름이_보인다() {
        // given
        val userName = "ChoiSeongHoon"
        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(userName)

        // then
        composeTestRule
            .onNodeWithText(userName)
            .assertExists()
    }

    @Test
    fun 회원_이름_길이가_1이면_이름_유효성_검사_실패_메세지가_보인다() {
        // given
        val username = "일" // 길이 1
        val invalidSupportingText = "이름은 2~5자여야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 회원_이름_길이가_6이면_이름_유효성_검사_실패_메세지가_보인다() {
        // given
        val username = "일이삼사오육" // 길이 6
        val invalidSupportingText = "이름은 2~5자여야 합니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 회원_이름_길이가_3이면_이름_유효성_검사_실패_메세지가_보이지_않는다() {
        // given
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val username = "최성훈" // 길이 3
        val invalidSupportingText = context.getString(R.string.sign_up_invalid_user_name_length)

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertDoesNotExist()
    }

    @Test
    fun 이름에는_숫자가_포함될_수_없다() {
        // given
        val username = "최성2" // 이름에 숫자 포함
        val invalidSupportingText = "이름에는 숫자나 기호가 포함될 수 없습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }

    @Test
    fun 이름에는_문자가_포함될_수_없다() {
        // given
        val username = "최성^" // 이름에 기호 포함
        val invalidSupportingText = "이름에는 숫자나 기호가 포함될 수 없습니다."

        composeTestRule.setContent {
            MaterialTheme {
                SignUpScreen()
            }
        }

        // when
        composeTestRule
            .onNodeWithText("Username")
            .performTextInput(username)

        // then
        composeTestRule
            .onNodeWithText(invalidSupportingText)
            .assertExists()
    }
}