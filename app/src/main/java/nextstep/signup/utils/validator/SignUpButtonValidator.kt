package nextstep.signup.utils.validator

import nextstep.signup.ui.model.SignUpInputModel
import nextstep.signup.ui.model.SignUpInputType

fun SignUpInputModel.isEnabled(): Boolean {
    return SignUpInputType.entries.all { it.isValid(this) }
}
