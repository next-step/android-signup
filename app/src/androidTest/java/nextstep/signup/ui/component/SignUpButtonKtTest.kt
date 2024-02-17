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
    private val userName = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(
                userName = userName.value,
                email = email.value,
                password = password.value,
                passwordConfirm = passwordConfirm.value
            )
        }
    }

    @Test
    fun 모든_입력값이_유효화면_버튼이_활성화된다() {
        // given

        // when
        userName.value = "wisem"
        email.value = "wisemuji@woowahan.com"
        password.value = "qwer1234"
        passwordConfirm.value = "qwer1234"

        // then
        composeTestRule
            .onNodeWithTag("signUpButton")
            .assertIsEnabled()
    }

    @Test
    fun 입력값이_하날나도_유효하지않으면_버튼이_비활성화된다() {
        // given

        // when
        userName.value = "wisem"
        email.value = "wisemuji#woowahan.com"
        password.value = "qwer1234"
        passwordConfirm.value = "qwer1234"

        // then
        composeTestRule
            .onNodeWithTag("signUpButton")
            .assertIsNotEnabled()
    }
}
