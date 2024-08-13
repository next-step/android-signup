package nextstep.signup.ui.component

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun SignUpButton(
    colors: ButtonColors,
    enabled: Boolean,
    modifier: Modifier = Modifier
) {
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) {
        Button(
            onClick = {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        message = context.getString(R.string.signupSuccessSnackBarContentDescription),
                        actionLabel = context.getString(R.string.signupSuccessSnackBarActionLabel),
                        duration = SnackbarDuration.Short
                    )
                }
            },
            colors = colors,
            content = {
                Text(
                    text = stringResource(id = R.string.signUp)
                )
            },
            enabled = enabled,
            modifier = modifier
        )
    }
}

@Preview
@Composable
private fun SignUpButtonPreview() {
    SignUpButton(
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