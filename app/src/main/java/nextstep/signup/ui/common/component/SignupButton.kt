package nextstep.signup.ui.common.component

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.RobotoMedium

@Composable
fun SignupButton(
    buttonText: String,
    buttonTextColor: Color,
    onButtonClick: () -> Unit,
    containerColor: Color = Color.Unspecified,
    enabled: Boolean = true,
    componentDescription: String = stringResource(R.string.signup_default_description),
) {
    Button(
        enabled = enabled,
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(containerColor = containerColor),
        contentPadding = PaddingValues(vertical = 15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .semantics { contentDescription = componentDescription },
    ) {
        Text(
            text = buttonText,
            fontSize = 14.sp,
            color = buttonTextColor,
            fontFamily = RobotoMedium,
        )
    }
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignupButton(
        buttonText = stringResource(R.string.signup_button),
        buttonTextColor = Color.White,
        containerColor = Blue50,
        onButtonClick = {},
    )
}