package nextstep.signup

import org.junit.Assert
import org.junit.Test

class EmailValidationTest {

    @Test
    fun 이메일_형식을_지켜야_한다() {
        val email = "com_compose@android.com"

        Assert.assertTrue(InputValidation.validateEmail(email) == ValidationResult.SUCCESS)
    }

    @Test
    fun 이메일_형식을_지키지_않으면_에러메시지가_노출된다() {
        val email = "com_compose.com"

        Assert.assertTrue(InputValidation.validateEmail(email) == ValidationResult.EMAIL_INVALID_FORMAT)
    }
}