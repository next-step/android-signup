package nextstep.signup.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.R


@Composable
fun SignUpTitleText(text: String) {
    Text(
        text = text,
        textAlign = TextAlign.Center,
        style = TextStyle(
            fontSize = 26.sp, fontWeight = FontWeight.Bold, fontFamily = FontFamily.SansSerif
        ),
    )
}

@Composable
private fun SignUpTextField(
    modifier: Modifier = Modifier,
    text: String,
    onValueChange: (String) -> Unit,
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val customColors = TextFieldDefaults.colors(
        focusedIndicatorColor = colorResource(id = R.color.blue_50),
        cursorColor = colorResource(id = R.color.blue_50),
        focusedLabelColor = colorResource(id = R.color.blue_50)
    )
    TextField(
        value = text,
        onValueChange = onValueChange,
        label = { Text(label) },
        modifier = modifier.fillMaxWidth(),
        singleLine = true,
        visualTransformation = visualTransformation,
        keyboardOptions = keyboardOptions,
        colors = customColors,
        shape = RoundedCornerShape(topStart = 4.dp, topEnd = 4.dp)
    )
}

@Preview
@Composable
private fun PreviewSignUpTitleText() {
    SignUpTitleText(text = stringResource(R.string.sign_up_text_title))
}

@Preview
@Composable
private fun PreviewSignUpTextField() {
    SignUpTextField(text = "compose", onValueChange = {}, label = "Username")
}
