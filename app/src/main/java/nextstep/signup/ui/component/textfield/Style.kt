package nextstep.signup.ui.component.textfield

import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import nextstep.signup.ui.theme.SignupBlue

@Composable
fun commonTextFieldColors() = TextFieldDefaults.colors(
    focusedIndicatorColor = SignupBlue,
    unfocusedIndicatorColor = Color.Black,
    focusedLabelColor = SignupBlue,
    unfocusedLabelColor = Color.Black,
    cursorColor = SignupBlue
)
