package nextstep.signup.study

import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Rule
import org.junit.Test

class RecompositionTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private var username by mutableStateOf("")
    private var other by mutableStateOf("")
    private var count = 0

    @Composable
    private fun UsernameTextField(
        username: String,
        other: String,
    ) {
        val usernameLengthError = (username.length !in 2..5).also { count++ }
        TextField(
            value = username,
            onValueChange = { },
            isError = usernameLengthError,
        )
    }

    @Composable
    private fun UsernameTextFieldWithRemember(
        username: String,
        other: String,
    ) {
        val usernameLengthError = remember(username) {
            (username.length !in 2..5).also { count++ }
        }
        TextField(
            value = username,
            onValueChange = { },
            isError = usernameLengthError,
        )
    }

    @Test
    fun 리컴포지션될때_매번_유효성_검사() {
        composeTestRule.setContent {
            UsernameTextField(username = username, other = other)
        }
        count = 0

        username = "김컴포즈"
        composeTestRule.waitForIdle()
        // JUnit의 assertion을 활용하는 경우 UI 업데이트가 완료되지 않을 수 있다.
        // 다음과 같이 수동 동기화 후 assertion을 진행
        assert(count == 1)

        other = "다른 값"
        composeTestRule.waitForIdle()
        assert(count == 2)
    }

    @Test
    fun 특정_값_변경시만_유효성_검사() {
        composeTestRule.setContent {
            UsernameTextFieldWithRemember(username = username, other = other)
        }
        count = 0

        username = "김컴포즈"
        composeTestRule.waitForIdle()
        assert(count == 1)

        other = "다른 값"
        composeTestRule.waitForIdle()
        assert(count == 1)
    }
}
