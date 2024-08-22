package nextstep.signup.ui

import androidx.lifecycle.ViewModel

class SignUpViewModel : ViewModel() {
    var userName: String = ""
    var supportingText: String = ""
    var email: String = ""
    var password: String = ""
    var passwordConfirm: String = ""
}