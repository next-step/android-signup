package nextstep.signup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.BlueGrey20
import nextstep.signup.ui.theme.roboto

@Composable
fun SignUpScreen(
    paddingValues: PaddingValues = PaddingValues(0.dp),
) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(paddingValues)
            .padding(horizontal = 32.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = stringResource(R.string.sign_up_title),
            fontSize = 26.sp,
            fontFamily = roboto,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(40.dp))

        Column(
            verticalArrangement = Arrangement.spacedBy(36.dp),
        ) {
            SignUpInputForm(label = stringResource(R.string.sign_up_user_name_label))
            SignUpInputForm(label = stringResource(R.string.sign_up_email_label))
            SignUpInputForm(
                label = stringResource(R.string.sign_up_password_label),
                visualTransformation = PasswordVisualTransformation()
            )
            SignUpInputForm(
                label = stringResource(R.string.sign_up_password_confirm_label),
                visualTransformation = PasswordVisualTransformation()
            )
        }

        Spacer(modifier = Modifier.height(42.dp))

        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Blue50,
            )
        ) {
            Text(stringResource(R.string.btn_sign_up))
        }
    }
}

@Composable
private fun SignUpInputForm(
    label: String,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    modifier: Modifier = Modifier
) {
    var input by remember { mutableStateOf("") }

    TextField(
        value = input,
        onValueChange = { input = it },
        label = { Text(label) },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = BlueGrey20,
            focusedContainerColor = BlueGrey20,
            focusedIndicatorColor = Blue50,
            focusedLabelColor = Blue50
        ),
        visualTransformation = visualTransformation,
        modifier = modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun SignUpScreenPreview() {
    MaterialTheme {
        SignUpScreen(PaddingValues())
    }
}

@Preview
@Composable
private fun SignUpInputFormPreview() {
    MaterialTheme {
        SignUpInputForm(label = "Username")
    }
}
