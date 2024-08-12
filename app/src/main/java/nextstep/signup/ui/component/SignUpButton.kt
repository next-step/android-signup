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
import androidx.lifecycle.viewmodel.compose.viewModel
import nextstep.signup.R
import nextstep.signup.SignUpUiState
import nextstep.signup.SignUpViewModel

@Composable
fun SignUpButton(
    onclick:() -> Unit,
    colors: ButtonColors,
    signUpUiState: SignUpUiState,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onclick,
        colors = colors,
        content = {
            Text(
                text = stringResource(id = R.string.signUp)
            )
        },
        enabled = signUpUiState.isSignUpButtonEnabled,
        modifier = modifier
    )
}

@SuppressLint("StateFlowValueCalledInComposition")
@Preview
@Composable
private fun SignUpButtonPreview() {
    val viewmodel: SignUpViewModel = viewModel()

    SignUpButton(
        onclick = {},
        colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            disabledContainerColor = Color.LightGray,
            disabledContentColor = Color.Gray
        ),
        signUpUiState = viewmodel.uiState.value,
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp)
            .padding(20.dp)
    )
}