package nextstep.signup.ui.signup

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.domain.SignUpValidator
import nextstep.signup.model.SignUpParams
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val enabled = mutableStateOf(false)

    @Before
    fun setup() {
        composeTestRule.setContent {
            Button(
                onClick = {  },
                enabled = enabled.value,
            ) {
                Text(text = "abc")
            }
        }
    }

    @Test
    fun 모든값이_유효하면_버튼은_활성화_상태이다() {
        // given
        val username = "jihoi"
        val email = "jihoi.kang@gmail.com"
        val password = "aa120000"
        val passwordConfirm = "aa120000"

        // when
        enabled.value = SignUpValidator.valid(SignUpParams(username, email, password, passwordConfirm))

        // then
        composeTestRule
            .onNodeWithText("abc")
            .assertIsEnabled()
    }

    @Test
    fun 사용자_이름이_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        val username = "jihoik"
        val email = "jihoi.kang@gmail.com"
        val password = "aa120000"
        val passwordConfirm = "aa120000"

        // when
        enabled.value = SignUpValidator.valid(SignUpParams(username, email, password, passwordConfirm))

        // then
        composeTestRule
            .onNodeWithText("abc")
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        val username = "jihoi"
        val email = "jihoi.kang"
        val password = "aa120000"
        val passwordConfirm = "aa120000"

        // when
        enabled.value = SignUpValidator.valid(SignUpParams(username, email, password, passwordConfirm))

        // then
        composeTestRule
            .onNodeWithText("abc")
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        val username = "jihoi"
        val email = "jihoi.kang@gmail.com"
        val password = "aa12"
        val passwordConfirm = "aa12"

        // when
        enabled.value = SignUpValidator.valid(SignUpParams(username, email, password, passwordConfirm))

        // then
        composeTestRule
            .onNodeWithText("abc")
            .assertIsNotEnabled()
    }

    @Test
    fun 두_비밀번호가_일치하지_않으면_버튼은_비활성화_상태이다() {
        // given
        val username = "jihoi"
        val email = "jihoi.kang@gmail.com"
        val password = "aa120000"
        val passwordConfirm = "aa120001"

        // when
        enabled.value = SignUpValidator.valid(SignUpParams(username, email, password, passwordConfirm))

        // then
        composeTestRule
            .onNodeWithText("abc")
            .assertIsNotEnabled()
    }

}