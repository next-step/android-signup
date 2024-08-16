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
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.RobotoMedium

@Composable
fun SignupButton(
    buttonText: String,
    buttonTextFontSize: TextUnit,
    buttonTextFontFamily: FontFamily,
    buttonTextColor: Color,
    onButtonClick: () -> Unit,
    containerColor: Color = Color.Unspecified,
    contentColor: Color = Color.Unspecified,
    disabledContainerColor: Color = Color.Unspecified,
    disabledContentColor: Color = Color.Unspecified,
    buttonVerticalPadding: Dp = 0.dp,
    buttonHorizontalPadding: Dp = 0.dp,
    enabled: Boolean = true,
    modifier: Modifier,
    componentDescription: String = stringResource(R.string.signup_default_description),
) {
    Button(
        enabled = enabled,
        onClick = onButtonClick,
        colors = ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContentColor = disabledContentColor,
            disabledContainerColor = disabledContainerColor,
        ),
        contentPadding = PaddingValues(
            vertical = buttonVerticalPadding,
            horizontal = buttonHorizontalPadding,
        ),
        modifier = modifier.semantics { contentDescription = componentDescription },
    ) {
        Text(
            text = buttonText,
            fontSize = buttonTextFontSize,
            color = buttonTextColor,
            fontFamily = buttonTextFontFamily,
        )
    }
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignupButton(
        buttonText = stringResource(R.string.signup_button),
        buttonTextFontSize = 14.sp,
        buttonTextFontFamily = RobotoMedium,
        buttonTextColor = Color.White,
        buttonVerticalPadding = 15.dp,
        containerColor = Blue50,
        onButtonClick = { },
        modifier = Modifier.fillMaxWidth()
    )
}