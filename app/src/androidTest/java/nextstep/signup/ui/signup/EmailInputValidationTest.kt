package nextstep.signup.ui.signup

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import nextstep.signup.R
import nextstep.signup.ui.component.BaseTextField
import nextstep.signup.ui.component.EmailTextField
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class EmailInputValidationTest {

    @get:Rule
    val composeTestRule = createComposeRule()
    private val email = mutableStateOf("")

    @Before
    fun setup() {
        composeTestRule.setContent {
            EmailTextField(
                value = email.value,
                onValueChange = {},
            )
        }
    }

    @Test
    fun 이메일값이_유효하면_에러가_표시되지_않는다() {
        // when
        email.value = "jihoi.kang@gmail.com"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_REGEX_ERROR)
            .assertDoesNotExist()
    }

    @Test
    fun 이메일이_유효하지_않으면_에러가_표시된다_CASE1() {
        // when
        email.value = "jihoi.kang@"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_REGEX_ERROR)
            .assertExists()
    }

    @Test
    fun 이메일이_유효하지_않으면_에러가_표시된다_CASE2() {
        // when
        email.value = "jihoi.kang@gmail"

        // then
        composeTestRule
            .onNodeWithText(EMAIL_REGEX_ERROR)
            .assertExists()
    }

    companion object {
        private const val EMAIL_REGEX_ERROR = "이메일 형식이 올바르지 않습니다."
    }

}