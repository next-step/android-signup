package nextstep.signup.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import nextstep.signup.ui.component.SIGN_UP_BUTTON_TAG
import nextstep.signup.ui.component.SignUpButton
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val username = mutableStateOf("")
    private val email = mutableStateOf("")
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")
    private val clicked = mutableStateOf(false)

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpButton(
                username = username.value,
                email = email.value,
                password = password.value,
                passwordConfirm = passwordConfirm.value,
                onClick = { clicked.value = true },
            )
        }
    }

    @Test
    fun 모든값이_유효하면_버튼은_활성화_상태이다() {
        // given
        username.value = "jihoi"
        email.value = "jihoi.kang@gmail.com"
        password.value = "aa120000"
        passwordConfirm.value = "aa120000"

        // when

        // then
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .assertIsEnabled()
    }

    @Test
    fun 사용자_이름이_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        username.value = "jihoik"
        email.value = "jihoi.kang@gmail.com"
        password.value = "aa120000"
        passwordConfirm.value = "aa120000"

        // when

        // then
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun 이메일이_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        username.value = "jihoi"
        email.value = "jihoi.kang"
        password.value = "aa120000"
        passwordConfirm.value = "aa120000"

        // when

        // then
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun 비밀번호가_유효하지_않으면_버튼은_비활성화_상태이다() {
        // given
        username.value = "jihoi"
        email.value = "jihoi.kang@gmail.com"
        password.value = "aa12"
        passwordConfirm.value = "aa12"

        // when

        // then
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun 두_비밀번호가_일치하지_않으면_버튼은_비활성화_상태이다() {
        // given
        username.value = "jihoi"
        email.value = "jihoi.kang@gmail.com"
        password.value = "aa120000"
        passwordConfirm.value = "aa120001"

        // when

        // then
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .assertIsNotEnabled()
    }

    @Test
    fun 버튼을_누르면_clicked는_true가_된다() {
        // given
        username.value = "jihoi"
        email.value = "jihoi.kang@gmail.com"
        password.value = "aa120000"
        passwordConfirm.value = "aa120000"

        // when
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .performClick()

        // then
        Assert.assertEquals(clicked.value, true)
    }

    @Test
    fun 버튼이_비활성화인_상태에서_버튼을_눌러도_clicked는_바뀌지않고_false이다() {
        // given
        username.value = ""
        email.value = ""
        password.value = ""
        passwordConfirm.value = ""

        // when
        composeTestRule
            .onNodeWithTag(SIGN_UP_BUTTON_TAG)
            .performClick()

        // then
        Assert.assertEquals(clicked.value, false)
    }

}