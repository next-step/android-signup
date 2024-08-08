package nextstep.signup.ui.component

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
fun UsernameTextField(username: String) {
    // TODO: Implement UsernameTextField
    SignUpTextField(
        value = username,
        onValueChange = {},
        label = { Text("이름") },
    )
}
