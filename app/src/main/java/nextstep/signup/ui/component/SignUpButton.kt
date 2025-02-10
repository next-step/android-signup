package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.Typography

@Composable
fun SignUpButton(
    enabled: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        modifier = modifier.fillMaxWidth(),
        onClick = onClick,
        enabled = enabled,
    ) {
        Text(
            modifier = Modifier.padding(vertical = 10.dp),
            text = stringResource(R.string.sign_up_button_text),
            style = Typography.titleSmall,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignUpButtonPreview() {
    SignupTheme {
        SignUpButton(
            enabled = true,
            onClick = {}
        )
    }
}