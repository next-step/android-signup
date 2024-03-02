package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class PasswordConfirmInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val password = mutableStateOf("")
    private val passwordConfirm = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            PasswordConfirmInputField(
                password = password.value,
                passwordConfirm = passwordConfirm.value,
                onPasswordConfirmChange = { passwordConfirm.value = it }
            )
        }
    }

    @Test
    fun 패스워드와_동일한_패스워드확인_을_입력하면_에러가_보이지않는다() {
        // given
        val inputPassword = "englishAnd123"
        val inputPasswordConfirm = "englishAnd123"

        // when
        password.value = inputPassword
        passwordConfirm.value = inputPasswordConfirm

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_not_matched))
            .assertDoesNotExist()
        composeTestRule
            .onNodeWithText('\u2022'.toString().repeat(inputPasswordConfirm.length))
            .assertExists()
    }

    @Test
    fun 동일하게_입력된_패스워드확인을_변경되면_에러가발생한다() {
        // given
        패스워드와_동일한_패스워드확인_을_입력하면_에러가_보이지않는다()

        // when
        passwordConfirm.value += "테스트"

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_password_not_matched))
            .assertExists()
    }

}
