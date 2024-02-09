package nextstep.signup.signup

import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpScreenTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    lateinit var signUpViewModel: SignUpViewModel

    @Before
    fun setUp() {
        signUpViewModel = SignUpViewModel()

        composeTestRule.setContent {
            SignUpScreen(signUpViewModel = signUpViewModel)
        }
    }

    @Test
    fun username의_길이는_2이상_5이하이다() {
        // when
        signUpViewModel.updateUserName("마리빈")

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun username의_길이가_2미만_5초과이면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updateUserName("malibin")

        // then
        composeTestRule
            .onNodeWithText("이름은 2~5자여야 합니다.")
            .assertExists()
    }

    @Test
    fun username은_영문_또는_한글만_입력할_수_있다() {
        // when
        signUpViewModel.updateUserName("말li빈")

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun username이_숫자_혹은_기호를_포함하면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updateUserName("말#1")

        // then
        composeTestRule
            .onNodeWithText("이름에는 숫자나 기호가 포함될 수 없습니다.")
            .assertExists()
    }

    @Test
    fun 이메일_형식이_올바르지_않으면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updateEmail("gmail")

        // then
        composeTestRule
            .onNodeWithText("이메일 형식이 올바르지 않습니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호의_길이는_8이상_16이하_이다() {
        // when
        signUpViewModel.updatePassword("1234567a")

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호의_길이가_8미만_16초과이면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updatePassword("12a")

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 8~16자여야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호가_영문과_숫자를_모두_포함하지_않으면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updatePassword("1234567890")

        // then
        composeTestRule
            .onNodeWithText("비밀번호는 영문과 숫자를 포함해야 합니다.")
            .assertExists()
    }

    @Test
    fun 비밀번호_확인_입력값은_비밀번호_입력값과_같아야_한다() {
        // when
        signUpViewModel.updatePassword("00000000a")
        signUpViewModel.updatePasswordConfirm("00000000a")

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertDoesNotExist()
    }

    @Test
    fun 비밀번호_확인_입력값이_비밀번호_입력값과_다르면_에러_문구를_표시한다() {
        // when
        signUpViewModel.updatePassword("00000000a")
        signUpViewModel.updatePasswordConfirm("00000000BB")

        // then
        composeTestRule
            .onNodeWithText("비밀번호가 일치하지 않습니다.")
            .assertExists()
    }
}
