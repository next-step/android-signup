package nextstep.signup.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@Composable
fun SignUpButtonComponent(
    btnText: String,
    onClick: () -> Unit
) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        shape = RoundedCornerShape(50)
    ) {
        Text(btnText)
    }
}


@Preview(showBackground = true)
@Composable
fun SignUpButtonPreview() {
    SignUpButtonComponent(
        stringResource(R.string.btn_sign_up),
        {}
    )
}