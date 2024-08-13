package nextstep.signup

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
import androidx.compose.ui.test.assertIsNotDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.tooling.preview.Preview
import androidx.test.core.app.ApplicationProvider
import nextstep.signup.ui.screen.SignUpScreen
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class InputValidationTest {
    @get:Rule
    val composeTestRule = createComposeRule()
    var context: Context = ApplicationProvider.getApplicationContext()

    private val viewModel = SignUpViewModel()

    @Before
    fun setup() {
        composeTestRule.setContent {
            SignUpScreen(viewModel = viewModel)
        }
    }

    @Test
    fun 입력항목의_유효성_검사가_통과된다면_에러메시지가_노출되지_않는다() {
        // when : 사용자 이름이 2에서 5자를 넘어 유효성 검사를 통과한 경우
        viewModel.setUserName("김컴포즈")

        // then : 사용자 이름 에러 메시지가 노출되지 않는다
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_user_name))
            .assertIsNotDisplayed()
    }

    @Test
    fun 입력항목의_유효성_검사가_통과되지_않으면_에러메시지가_노출된다() {
        // when : 사용자 이름이 2에서 5자를 넘어 유효성 검사를 통과하지 못한 경우
        viewModel.setUserName("김컴포즈입니다")

        // then : 사용자 이름 에러 메시지가 노출된다
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_user_name))
            .assertIsDisplayed()
    }

    @Test
    fun 모든_입력항목의_유효성_검사가_통과될_때_회원가입_버튼이_활성화된다() {
        // when
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail.com")
        viewModel.setPassword("12345678dd")
        viewModel.setPasswordConfirm("12345678dd")

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsEnabled()
    }

    @Test
    fun 입력항목중_하나라도_통과하지_못한다면_회원가입_버튼은_비활성화된다() {
        // when : 이메일 형식이 올바르지 않은 경우
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail")
        viewModel.setPassword("12345678dd")
        viewModel.setPasswordConfirm("12345678dd")

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 활성화된_회원가입_버튼을_누르면_스낵바가_노출된다() {
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail.com")
        viewModel.setPassword("12345678dd")
        viewModel.setPasswordConfirm("12345678dd")

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .performClick()

        composeTestRule
            .onNodeWithText(context.getString(R.string.text_sign_up_success))
            .assertIsDisplayed()
    }

    @Test
    fun 비활성화된_회원가입_버튼을_누르면_스낵바가_노출되지_않는다() {
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail.com")
        viewModel.setPassword("12345678dd")
        viewModel.setPasswordConfirm("123456")

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .performClick()

        composeTestRule
            .onNodeWithText(context.getString(R.string.text_sign_up_success))
            .assertIsNotDisplayed()
    }
}

@Preview(
    showBackground = true,
    backgroundColor = 0xFFFFFFFF
)
@Composable
private fun test() {
    SignUpScreen(viewModel = SignUpViewModel())
}