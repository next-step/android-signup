package nextstep.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.test.assert
import androidx.compose.ui.test.hasText
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performTextClearance
import androidx.compose.ui.test.performTextInput
import nextstep.signup.ui.screen.UsernameTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class UsernameValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val username = mutableStateOf<String>("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            UsernameTextField(
                userName = username.value,
                onUsernameChange = { username.value = it },
                modifier = Modifier.testTag(USERNAME_TEST_TAG)
            )
        }
    }

    @Test
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        val username = "김컴포즈"

        // then
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(username)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(!hasText(USERNAME_LENGTH_ERROR))
    }

    @Test
    fun 사용자_이름이_2에서_5자_외인_경우_에러메시지가_노출된다() {
        //when
        val usernameUnder = "김"
        val usernameOver = "김컴포즈입니다"

        //then
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(usernameUnder)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(hasText(USERNAME_LENGTH_ERROR))
            .assertExists()

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextClearance()

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(usernameOver)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(hasText(USERNAME_LENGTH_ERROR))
            .assertExists()
    }

    @Test
    fun 사용자_이름이_숫자나_기호가_포함이_됐을떄_에러메시지가_노출된다() {
        // when
        val usernameIncludeNum = "김123"
        val usernameIncludeSign = "김!@#"
        val usernameIncludeNumAndSign = "김12#"

        // then
        /** 사용자 이름에 숫자가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(usernameIncludeNum)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(hasText(USERNAME_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextClearance()

        /** 사용자 이름에 기호가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(usernameIncludeSign)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(hasText(USERNAME_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextClearance()

        /** 사용자 이름에 숫자와 기호가 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .performTextInput(usernameIncludeNumAndSign)

        composeTestRule.onNodeWithTag(USERNAME_TEST_TAG)
            .assert(hasText(USERNAME_REGEX_ERROR))
    }

    companion object {
        const val USERNAME_TEST_TAG = "Username"
        const val USERNAME_LENGTH_ERROR = "이름은 2~5자여야 합니다."
        const val USERNAME_REGEX_ERROR = "이름에는 숫자나 기호가 포함될 수 없습니다."
    }
}