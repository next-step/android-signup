package nextstep.signup

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.screen.InputField
import nextstep.signup.ui.screen.RegexPattern.EMAIL_REGEX
import nextstep.signup.ui.screen.RegexPattern.USERNAME_REGEX
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val userErrorMsg = mutableStateOf<String?>(null)

    private val email = mutableStateOf("")
    private val emailErrorMsg = mutableStateOf<String?>(null)

    @Before
    fun setUp() {
        composeTestRule.setContent {
            Column {
                InputField(
                    label = "Username",
                    value = username.value,
                    errorMsg = userErrorMsg.value,
                    onValueChange = {
                        username.value = it
                        userErrorMsg.value = when {
                            it.length !in 2..5 -> USERNAME_LENGTH_ERROR
                            !it.matches(Regex(USERNAME_REGEX)) -> USERNAME_REGEX_ERROR
                            else -> null
                        }
                    },
                    modifier = Modifier.testTag("Username")
                )
                InputField(
                    label = "Email",
                    value = email.value,
                    errorMsg = emailErrorMsg.value,
                    onValueChange = {
                        email.value = it
                        emailErrorMsg.value = when {
                            !it.matches(Regex(EMAIL_REGEX)) -> EMAIL_REGEX_ERROR
                            else -> null
                        }
                    },
                    modifier = Modifier.testTag("Email")
                )
            }
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        val username = "김컴포즈"
        val usernameLengthOver = "김컴포즈입니다"

        // then
        /** 사용자 이름이 2~5자 이내인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(username)

        composeTestRule.onNodeWithTag("Username")
            .assert(!hasText(USERNAME_LENGTH_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Username")
            .performTextClearance()

        /** 사용자 이름이 2~5자 외인 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameLengthOver)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_LENGTH_ERROR))
            .assertExists()
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함이_됐을떄_에러메시지가_노출된다() {
        // when
        val usernameIncludeNum = "김123"
        val usernameIncludeSign = "김!@#"

        // then
        /** 사용자 이름에 숫자가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameIncludeNum)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Username")
            .performTextClearance()

        /** 사용자 이름에 기호가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag("Username")
            .performTextInput(usernameIncludeSign)

        composeTestRule.onNodeWithTag("Username")
            .assert(hasText(USERNAME_REGEX_ERROR))
    }

    @Test
    fun 이메일_형식이_올바라야_한다() {
        // when
        val email = "test@test.net"
        val emailInvalid = "test@net"

        // then
        /** 이메일 형식이 올바른 경우 테스트 **/
        composeTestRule.onNodeWithTag("Email")
            .performTextInput(email)

        composeTestRule.onNodeWithTag("Email")
            .assert(!hasText(EMAIL_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag("Email")
            .performTextClearance()

        /** 이메일 형식이 올바르지 않은 경우 테스트 **/
        composeTestRule.onNodeWithTag("Email")
            .performTextInput(emailInvalid)

        composeTestRule.onNodeWithTag("Email")
            .assert(hasText(EMAIL_REGEX_ERROR))
    }

    companion object {
        const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        const val USERNAME_REGEX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."

        const val EMAIL_REGEX_ERROR = "이메일 형식이 올바르지 않습니다."
    }
}