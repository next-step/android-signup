package nextstep.signup

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import kotlinx.coroutines.launch
import nextstep.signup.ui.screen.SignUpButton
import nextstep.signup.ui.utils.ValidateUtils
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    var username = mutableStateOf("")
    var email = mutableStateOf("")
    var password = mutableStateOf("")
    var confirmPassword = mutableStateOf("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            val snackbarHostState = remember { SnackbarHostState() }
            val coroutineScope = rememberCoroutineScope()

            Scaffold(
                snackbarHost = {
                    SnackbarHost(
                        hostState = snackbarHostState,
                        modifier = Modifier.testTag(SNACKBAR_TEST_TAG)
                    )
                },
            ) {
                SignUpButton(
                    isEnabled = ValidateUtils.isValidAll(
                        username = username.value,
                        email = email.value,
                        password = password.value,
                        confirmPassword = confirmPassword.value
                    ),
                    modifier = Modifier
                        .testTag(BUTTON_TEST_TAG)
                        .padding(it)
                ) {
                    coroutineScope.launch {
                        snackbarHostState.showSnackbar(SNACKBAR_MESSAGE)
                    }
                }
            }
        }
    }

    @Test
    fun 모든_유효성을_만족하면_버튼이_활성화된다() {
        // when
        username.value = "김컴포즈"
        email.value = "test@test.com"
        password.value = "qwer1234"
        confirmPassword.value = "qwer1234"

        // then
        composeTestRule.onNodeWithTag(BUTTON_TEST_TAG)
            .assertIsEnabled()
    }

    @Test
    fun 모든_유효성을_만족하지_못하면_버튼이_활성화되지_않는다() {
        // when
        username.value = "김컴포즈"
        email.value = "test@test.com"
        password.value = "qwer12345"
        confirmPassword.value = "qwer1234"

        // then
        composeTestRule.onNodeWithTag(BUTTON_TEST_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun 회원가입_버튼을_클릭하면_스낵바가_출력된다() {
        // when
        username.value = "김컴포즈"
        email.value = "test@test.com"
        password.value = "qwer1234"
        confirmPassword.value = "qwer1234"

        // then
        composeTestRule.onNodeWithTag(BUTTON_TEST_TAG).performClick()
        composeTestRule.onNodeWithTag(SNACKBAR_TEST_TAG).assertIsDisplayed()
        composeTestRule.onNodeWithText(SNACKBAR_MESSAGE).assertIsDisplayed()
    }

    companion object {
        const val BUTTON_TEST_TAG = "SignUpButton"
        const val SNACKBAR_TEST_TAG = "Snackbar"

        const val SNACKBAR_MESSAGE = "회원가입 완료"
    }
}