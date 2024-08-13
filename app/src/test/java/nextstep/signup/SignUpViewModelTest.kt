package nextstep.signup

import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

class SignUpViewModelTest {
    private val viewModel = SignUpViewModel()

    @Test
    fun 사용자_이름은_2에서_5자일_경우_유효성_검사를_통과한다() {
        // given
        viewModel.userName = "김컴포즈"

        // when
        val actual = viewModel.validateUserName()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 사용자_이름이_2에서_5자를_넘을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.userName = "김컴포즈입니다"

        // when
        val actual = viewModel.validateUserName()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 이메일_형식이_올바를_경우_유효성_검사를_통과한다() {
        // given
        viewModel.email = "oyj7677@gmail.com"

        // when
        val actual = viewModel.validateEmail()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 이메일_형식이_올바르지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.email = "oyj7677@gmail"

        // when
        val actual = viewModel.validateEmail()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 비밀번호는_8에서_16자이고_영문_숫자_조합이어야_한다() {
        // given
        viewModel.password = "1234qwer"

        // when
        val actual = viewModel.validatePassword()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 비밀번호가_8에서_16자가_아닐_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.password = "12a34"

        // when
        val actual = viewModel.validatePassword()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 비밀번호는_영문과_숫자를_포함하지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.password = "12345678"

        // when
        val actual = viewModel.validatePassword()

        // then
        assertThat(actual).isFalse()
    }


    @Test
    fun 비밀번호와_비밀번호_확인값이_일치할_경우_유효성_검사를_통과한다() {
        // given
        viewModel.password = "1234qwer"
        viewModel.passwordConfirm = "1234qwer"

        // when
        val actual = viewModel.validatePasswordConfirm()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 비밀번호와_비밀번호_확인값이_일치하지_않을_경우_유효성_검사를_통과하지_못한다() {
        // given
        viewModel.password = "1234qwer"
        viewModel.passwordConfirm = "1234qwer!"

        // when
        val actual = viewModel.validatePasswordConfirm()

        // then
        assertThat(actual).isFalse()
    }

    @Test
    fun 모든_입력값이_올바를_경우_전체_유효성_검사를_통과한다() {
        // given
        viewModel.userName = "김컴포즈"
        viewModel.email = "oyj7677@gmail.com"
        viewModel.password = "1234qwer"
        viewModel.passwordConfirm = "1234qwer"

        // when
        val actual = viewModel.validateAll()

        // then
        assertThat(actual).isTrue()
    }

    @Test
    fun 모든_입력값이_올바르지_않을_경우_전체_유효성_검사를_통과하지_못한다() {
        // given : 비밀번호와 비밀번호 확인 값이 다름
        viewModel.userName = "김컴포즈입니다"
        viewModel.email = "oyj7677@gmail"
        viewModel.password = "1234qwer"
        viewModel.passwordConfirm = "1234qwer!"

        // when
        val actual = viewModel.validateAll()

        // then
        assertThat(actual).isFalse()
    }
}