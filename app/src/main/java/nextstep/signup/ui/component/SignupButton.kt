package nextstep.signup.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Pink80
import nextstep.signup.ui.theme.SignupTheme

@Composable
fun SignupButton() {
    Button(
        onClick = { /*TODO*/ },
        modifier = Modifier
            .fillMaxWidth(1.0F),
        colors = ButtonColors(
            containerColor = Color(Pink80.value),
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = stringResource(id = R.string.signup_button),
            modifier = Modifier
                .padding(vertical = 15.dp),
            color = Color.White,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight(500),
                letterSpacing = 0.1.sp
            )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupButtonPreview() {
    SignupTheme {
        SignupButton()
    }
}