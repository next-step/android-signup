package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.ComposeContentTestRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonKtTest {
    @get:Rule
    val composeTestRule: ComposeContentTestRule = createComposeRule()
    private val isEnabled = mutableStateOf(false)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(isEnabled = isEnabled.value)
        }
    }

    @Test
    fun 모든_입력값이_유효하면_버튼이_활성화된다() {
        // given

        // when
        isEnabled.value = true

        // then
        composeTestRule
            .onNodeWithTag("signUpButton")
            .assertIsEnabled()
    }

    @Test
    fun 입력값이_하나나도_유효하지않으면_버튼이_비활성화된다() {
        // given

        // when
        isEnabled.value = false

        // then
        composeTestRule
            .onNodeWithTag("signUpButton")
            .assertIsNotEnabled()
    }
}
