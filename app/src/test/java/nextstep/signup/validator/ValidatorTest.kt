package nextstep.signup.validator

import org.junit.Test

class ValidatorTest {

    @Test
    fun `이름이 2자 보다 적으면 안된다`() {
        // given
        val username = "김"

        // when
        val result = Validator.Username.checkCondition(username)

        //then
        assert(result == ValidateResult.INVALID_LENGTH_USERNAME)
    }

    @Test
    fun `이름이 6자 보다 많으면 안된다`() {
        // given
        val username = "가나가라마바사아"

        // when
        val result = Validator.Username.checkCondition(username)

        //then
        assert(result == ValidateResult.INVALID_LENGTH_USERNAME)
    }

    @Test
    fun `이름에는 숫자나 기호가 포함될 수 없다`() {
        // given
        val username = "1!2@"

        // when
        val result = Validator.Username.checkCondition(username)

        //then
        assert(result == ValidateResult.INVALID_FORMAT_USERNAME)
    }

    @Test
    fun `이메일은 형식이 맞아야한다`() {
        // given
        val email = "raindragonn@gmail.com"

        // when
        val result = Validator.Email.checkCondition(email)

        //then
        assert(result == ValidateResult.SUCCESS)
    }

    @Test
    fun `비밀번호는 8자보다 적으면 안된다`() {
        // given
        val password = "1q2w3e4"

        // when
        val result = Validator.Password.checkCondition(password)

        //then
        assert(result == ValidateResult.INVALID_LENGTH_PASSWORD)
    }

    @Test
    fun `비밀번호는 16자보다 많으면 안된다`() {
        // given
        val password = "1q2w3e4r5t6y7u8i9"

        // when
        val result = Validator.Password.checkCondition(password)

        //then
        assert(result == ValidateResult.INVALID_LENGTH_PASSWORD)
    }

    @Test
    fun `비밀번호는 영문을 포함하지 않으면 안된다`() {
        // given
        val password = "123456789"

        // when
        val result = Validator.Password.checkCondition(password)

        //then
        assert(result == ValidateResult.INVALID_FORMAT_PASSWORD)
    }

    @Test
    fun `비밀번호는 숫자를 포함하지 않으면 안된다`() {
        // given
        val password = "qwertyui"

        // when
        val result = Validator.Password.checkCondition(password)

        //then
        assert(result == ValidateResult.INVALID_FORMAT_PASSWORD)
    }

    @Test
    fun `비밀번호가 일치해야한다`() {
        // given
        val password = "1q2w3e4r"
        val passwordConfirm = "1q2w3e4r"

        // when
        val result = Validator.PasswordConfirm {
            password
        }.checkCondition(passwordConfirm)

        //then
        assert(result == ValidateResult.SUCCESS)
    }

}
