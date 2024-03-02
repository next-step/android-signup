package nextstep.signup.ui.component

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailTextInputFieldTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextInputField(
                email = email.value,
                onEmailChange = { email.value = it }
            )
        }
    }

    @Test
    fun 유효한_이메일_주소를_입력하면_에러가_보이지않는다() {
        // given
        val inputEmail = "email@google.com"

        // when
        email.value = inputEmail

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_email_invalid))
            .assertDoesNotExist()
    }

    @Test
    fun 유효하지_않은_이메일_주소를_입력하면_에러가_보인다() {
        // given
        val inputEmail = "email@googlecom"

        // when
        email.value = inputEmail

        //then
        composeTestRule
            .onNodeWithText(getString(id = R.string.signup_error_email_invalid))
            .assertExists()
    }

}
