package nextstep.signup.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpButton(
    onClick: () -> Unit,
    colors: ButtonColors,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onClick,
        colors = colors,
        content = { Text(text = stringResource(id = R.string.signUp)) },
        enabled = enabled,
        modifier = modifier
    )
}


@Preview
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
        onClick = { },
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        ),
        enabled = true,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp)
    )
}