package nextstep.signup.ui.component

import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Rule
import org.junit.Test

class SignUpCompleteSnackBarTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private var message = "회원가입을 완료했습니다."

    @Test
    fun 스낵바_노출() {
        // given
        composeTestRule.setContent {
            val snackBarHostState = SnackbarHostState()
            SnackbarHost(hostState = snackBarHostState)
            SignUpSnackBar(snackBarHostState)
        }

        // then
        composeTestRule
            .onNodeWithText(message)
            .assertIsDisplayed()
    }
}