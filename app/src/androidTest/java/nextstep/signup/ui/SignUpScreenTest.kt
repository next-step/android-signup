package nextstep.signup.ui

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import org.junit.Before
import org.junit.Rule

class SignUpScreenTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(
                username = username.value,
                email = email.value,
                password = password.value,
                passwordConfirm = passwordConfirm.value,
                onUsernameChange = { username.value = it },
                onEmailChange = { email.value = it },
                onPasswordChange = { password.value = it },
                onPasswordConfirmChange = { passwordConfirm.value = it },
            )
        }
    }

    // TODO:각 컴퍼넌트에서 테스트 진행하고 있음, 추후에 추가 예정
}
