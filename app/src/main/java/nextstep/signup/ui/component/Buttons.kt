package nextstep.signup.ui.component

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.theme.Blue50

@Composable
@Preview
fun SignUpButton(
    label: String = "Sign Up",
) {
    var enabled by remember { mutableStateOf(true) }

    Button(enabled = enabled, colors = ButtonDefaults.buttonColors(
        containerColor = Blue50,
    ), modifier = Modifier
        .width(296.dp)
        .height(50.dp), content = {
        Text(text = label)
    }, onClick = {})
}