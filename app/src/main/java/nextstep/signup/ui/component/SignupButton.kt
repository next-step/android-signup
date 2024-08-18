package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.DisableButton
import nextstep.signup.ui.theme.DisableButtonText

@Composable
fun SignUpButton(
    onClicked: () -> Unit,
    enabled: Boolean
) {
    Button(
        modifier = Modifier
            .testTag("signUpButton")
            .fillMaxWidth()
            .height(50.dp)
            .padding(top = 6.dp),
        onClick = onClicked,
        colors = ButtonDefaults.buttonColors(
            containerColor = Blue50,
            contentColor = Color.White,
            disabledContainerColor = DisableButton,
            disabledContentColor = DisableButtonText
        ),
        enabled = enabled
    ) {
        Text(
            text = stringResource(id = R.string.sign_up_button),
            fontSize = 14.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
        onClicked = {},
        enabled = true
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonDisablePreview() {
    SignUpButton(
        onClicked = {},
        enabled = false
    )
}
