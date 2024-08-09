package nextstep.signup.core.validation

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    EmailValidatorTest::class,
    NameValidatorTest::class,
    PasswordValidatorTest::class,
    PasswordMatchValidatorTest::class,
)
class ValidatorTest