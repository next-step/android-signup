package nextstep.signup.screen

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    EmailTextTextFieldTest::class,
    PasswordConfirmTextFieldTest::class,
    PasswordTextFieldTest::class,
    UserNameTextFieldTest::class,
)
class SingUpTextFieldTests
