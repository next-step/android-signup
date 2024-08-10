package nextstep.signup.study

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.unit.dp
import nextstep.signup.screen.SignUpTitle
import org.junit.Rule
import org.junit.Test

/**
 * Create Date: 2024. 8. 10.
 *
 * 회원가입 화면 테스트
 *
 * @author LeeDongHun
 *
 *
**/

class SignupScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()


    /**
     * 회원가입 title에 figma에 정의된
     * title text가  존재하는지 확인
    **/
    @Test
    fun signUpTitleExist() {
        composeTestRule.setContent {
            SignUpTitle(modifier = Modifier
                .fillMaxWidth()
                .padding(top = 70.dp))
        }
        composeTestRule
            .onNodeWithText("Welcome to Compose \uD83D\uDE80")
            .assertExists()
    }
}