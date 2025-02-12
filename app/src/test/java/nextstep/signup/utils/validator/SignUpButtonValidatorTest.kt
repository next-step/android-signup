package nextstep.signup.utils.validator

import junit.framework.TestCase.assertFalse
import junit.framework.TestCase.assertTrue
import nextstep.signup.ui.model.SignUpInputModel
import org.junit.Test

class SignUpButtonValidatorTest {

    @Test
    fun 입력값이_모두_유효하다면_enable() {
        // given
        val validModel = SignUpInputModel(
            username = "상아다",
            email = "abc@abc.com",
            password = "password1234",
            passwordConfirm = "password1234"
        )

        // then
        assertTrue(validModel.isEnabled())
    }

    @Test
    fun 입력값이_없다면_disabled() {
        // given
        val emptyModel = SignUpInputModel()

        // then
        assertFalse(emptyModel.isEnabled())
    }

    @Test
    fun 입력값이_하나라도_유효하지_않다면_disabled() {
        // given
        val validModel = SignUpInputModel(
            username = "상",
            email = "abc@abc.com",
            password = "password1234",
            passwordConfirm = "password1234"
        )

        // then
        assertFalse(validModel.isEnabled())
    }

    @Test
    fun 입력값이_하나라도_없다면_disabled() {
        // given
        val validModel = SignUpInputModel(
            username = "",
            email = "abc@abc.com",
            password = "password1234",
            passwordConfirm = "password1234"
        )

        // then
        assertFalse(validModel.isEnabled())
    }
}