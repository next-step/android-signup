package nextstep.signup

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsEnabled
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
    fun 사용자_이름은_2에서_5자여야_한다() {
        // when
        viewModel.userName = "김컴포즈"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name))
            .assertDoesNotExist()
    }

    @Test
    fun 사용자_이름이_2에서_5자가_아니면_에러메시지가_노출된다() {
        // when
        viewModel.userName = "김컴포즈입니다"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_user_name)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러메시지가_노출된다() {
        // when
        viewModel.email = "oyj7677@gmail"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email)).assertExists()
    }

    @Test
    fun 이메일_형식이_올바르다면_에러메시지가_노출되지_않는다() {
        // when
        viewModel.email = "oyj7677@gmail.com"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_email))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // when
        viewModel.password = "12345678dd"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password))
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아니면_에러메시지가_노출된다() {
        // when
        viewModel.password = "12345678901234567"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않는다면_에러메시지가_노출된다() {
        // when
        viewModel.password = "12345678"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_포함하지_않으면_에러메시지가_노출된다() {
        // when
        viewModel.password = "12345678@"

        // then
        composeTestRule.onNodeWithText(context.getString(R.string.err_msg_password)).assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치하지_않는다면_에러메시지가_노출된다() {
        // when
        viewModel.password = "12345678dd"
        viewModel.passwordConfirm = "12345678dd!"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertExists()
    }

    @Test
    fun 비밀번호와_비밀번호_확인이_일치한다면_에러메시지가_노출되지_않는다() {
        // when
        viewModel.password = "12345678dd"
        viewModel.passwordConfirm = "12345678dd"

        // then
        composeTestRule
            .onNodeWithText(context.getString(R.string.err_msg_password_confirm))
            .assertDoesNotExist()
    }

    @Test
    fun 모든_입력항목의_유효성_검사가_통과될_때_회원가입_버튼이_활성화된다() {
        // when
        viewModel.userName = "김컴포즈"
        viewModel.email = "oyj7677@gmail.com"
        viewModel.password = "12345678dd"
        viewModel.passwordConfirm = "12345678dd"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsEnabled()
    }

    @Test
    fun 입력항목중_하나라도_통과하지_못한다면_회원가입_버튼은_비활성화된다() {
        // when : 이메일 형식이 올바르지 않은 경우
        viewModel.userName = "김컴포즈"
        viewModel.email = "oyj7677@gmail"
        viewModel.password = "12345678dd"
        viewModel.passwordConfirm = "12345678dd"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .assertIsNotEnabled()
    }

    @Test
    fun 활성화된_회원가입_버튼을_누르면_스낵바가_노출된다() {
        viewModel.userName = "김컴포즈"
        viewModel.email = "oyj7677@gmail.com"
        viewModel.password = "12345678dd"
        viewModel.passwordConfirm = "12345678dd"

        composeTestRule
            .onNodeWithText(context.getString(R.string.sign_up))
            .performClick()

        composeTestRule
            .onNodeWithText(context.getString(R.string.text_sign_up_success))
            .assertIsDisplayed()
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