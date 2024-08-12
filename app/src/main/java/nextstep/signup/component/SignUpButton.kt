package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R

@Composable
fun SignUpButton(onClick: () -> Unit, modifier: Modifier = Modifier) {
    Button(modifier = modifier
        .fillMaxWidth()
        .height(50.dp),
        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.blue_50)),
        onClick = { onClick() }) {
        Text(
            text = stringResource(R.string.sign_up_button),
            color = Color.White,
            fontSize = 14.sp,
            style = MaterialTheme.typography.labelLarge
        )
    }
}


@Preview
@Composable
private fun PreviewSignUpButton() {
    SignUpButton(onClick = {})
}