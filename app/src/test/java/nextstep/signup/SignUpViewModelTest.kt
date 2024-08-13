package nextstep.signup

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SignUpViewModelTest {
    private val viewModel = SignUpViewModel()

    @Test
    fun 사용자_이름은_2에서_5자일_경우_유효성_검사를_통과한다() {
        // given
        viewModel.setUserName("김컴포즈")

        // when
        val actual = viewModel.isUserNameError

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 사용자_이름이_2에서_5자를_넘을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.setUserName("김컴포즈입니다")

        // when
        val actual = viewModel.isUserNameError

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 이메일_형식이_올바를_경우_유효성_검사를_통과한다() {
        // given
        viewModel.setEmail("oyj7677@gmail.com")

        // when
        val actual = viewModel.isEmailError

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.setEmail("oyj7677@gmail")

        // when
        val actual = viewModel.isEmailError

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // given
        viewModel.setPassword("1234qwer")

        // when
        val actual = viewModel.isPasswordError

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아닐_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.setPassword("12a34")

        // when
        val actual = viewModel.isPasswordError

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.setPassword("12345678")

        // when
        val actual = viewModel.isPasswordError

        // then
        assertThat(actual).isFalse()
    }


    @Test
    fun 비밀번호와_비밀번호_확인값이_일치할_경우_유효성_검사를_통과한다() {
        // given
        viewModel.setPassword("1234qwer")
        viewModel.setPasswordConfirm("1234qwer")

        // when
        val actual = viewModel.isPasswordConfirmError

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_일치하지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.setPassword("1234qwer")
        viewModel.setPasswordConfirm("1234qwer!")

        // when
        val actual = viewModel.isPasswordConfirmError

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 모든_입력값이_올바를_경우_전체_유효성_검사를_통과한다() {
        // given
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail.com")
        viewModel.setPassword("1234qwer")
        viewModel.setPasswordConfirm("1234qwer")

        // when
        val actual = viewModel.validateAll()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 모든_입력값이_올바르지_않을_경우_전체_유효성_검사를_통과하지_못한다() {
        // given : 비밀번호와 비밀번호 확인 값이 다름
        viewModel.setUserName("김컴포즈")
        viewModel.setEmail("oyj7677@gmail.com")
        viewModel.setPassword("1234qwer")
        viewModel.setPasswordConfirm("1234qwer!")

        // when
        val actual = viewModel.validateAll()

        // then
        assertThat(actual).isFalse()
    }
}