package nextstep.signup.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun UserRegisterTextField(
    value: String,
    labelText: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    errorMessage: String = "",
    visualTransformation: VisualTransformation = VisualTransformation.None,
) {
    Column(modifier = modifier) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            isError = errorMessage.isNotBlank(),
            label = { Text(labelText) },
            visualTransformation = visualTransformation,
            modifier = Modifier
                .fillMaxWidth()
                .height(53.dp),
            colors = TextFieldDefaults.colors(
                focusedIndicatorColor = Color.Cyan,
                focusedLabelColor = Color.Cyan,
                unfocusedIndicatorColor = Color.Black,
            )
        )
        if (errorMessage.isNotBlank()) {
            Text(
                errorMessage,
                style = TextStyle(
                    fontSize = 12.sp,
                    color = Color.Red
                ),
                textAlign = TextAlign.Start,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 10.dp, vertical = 2.dp)
                    .height(20.dp),
            )
        } else {
            Spacer(modifier = Modifier.height(20.dp))
        }
    }
}

@Preview
@Composable
private fun UserRegisterFieldPreview() {
    var input by rememberSaveable { mutableStateOf("") }

    UserRegisterTextField(
        value = input ,
        onValueChange = {input = it},
        labelText = "Username",
    )
}

@Preview
@Composable
private fun UserRegisterFieldErrorPreview() {
    var input by rememberSaveable { mutableStateOf("") }

    UserRegisterTextField(
        value = input ,
        onValueChange = {input = it},
        labelText = "Username",
        errorMessage = "error message"
    )
}
