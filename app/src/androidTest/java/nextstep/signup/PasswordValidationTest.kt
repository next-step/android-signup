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
import nextstep.signup.ui.screen.PasswordTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    private val password = mutableStateOf<String>("")

    @Before
    fun setUp() {
        composeTestRule.setContent {
            PasswordTextField(
                password = password.value,
                onPasswordChange = { password.value = it },
                modifier = Modifier.testTag(PASSWORD_TEST_TAG)
            )
        }
    }

    @Test
    fun 비밀번호는_8에서_16자여야_한다() {
        // when
        val password = "12345678"
        val passwordLengthOver = "12345678901234567"

        // then
        /** 비밀번호가 8~16자 이내인 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(password)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(!hasText(PASSWORD_LENGTH_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextClearance()

        /** 비밀번호가 8~16자 외인 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(passwordLengthOver)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(hasText(PASSWORD_LENGTH_ERROR))
    }

    @Test
    fun 비밀번호가_8에서_16자_외인_경우_오류메시지가_노출된다() {
        // when
        val passwordUnder = "1234567"
        val passwordOver = "12345678901234567"

        // then
        /** 비밀번호가 8자 미만인 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(passwordUnder)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(hasText(PASSWORD_LENGTH_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextClearance()

        /** 비밀번호가 16자 초과인 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(passwordOver)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(hasText(PASSWORD_LENGTH_ERROR))
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함해야_한다() {
        // when
        val password = "1234abcd"

        // then
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(password)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(!hasText(PASSWORD_REGEX_ERROR))
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_오류메시지가_노출된다() {
        // when
        val passwordChar = "fdsfdabcd"
        val passwordNum = "12341234"

        // then
        /** 비밀번호에 영문만 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(passwordChar)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(hasText(PASSWORD_REGEX_ERROR))

        // 텍스트 필드 초기화
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextClearance()

        /** 비밀번호에 숫자만 포함된 경우 테스트 **/
        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .performTextInput(passwordNum)

        composeTestRule.onNodeWithTag(PASSWORD_TEST_TAG)
            .assert(hasText(PASSWORD_REGEX_ERROR))
    }

    companion object {
        const val PASSWORD_TEST_TAG = "Password"

        const val PASSWORD_LENGTH_ERROR = "비밀번호는 8~16자여야 합니다."
        const val PASSWORD_REGEX_ERROR = "비밀번호는 영문과 숫자를 포함해야 합니다."
    }
}