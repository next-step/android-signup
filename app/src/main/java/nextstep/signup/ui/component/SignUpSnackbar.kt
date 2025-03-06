package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.R
import nextstep.signup.ui.theme.Blue50
import nextstep.signup.ui.theme.SignupTheme


@Composable
fun SignUpSnackbar(
    snackbarHostState: SnackbarHostState,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.BottomCenter
    ) {
        SnackbarHost(
            hostState = snackbarHostState,
            modifier = Modifier.padding(16.dp)
        ) {
            Snackbar(
                snackbarData = it,
                containerColor = Blue50,
                contentColor = Color.White,
            )
        }
    }
}

@Preview
@Composable
private fun SignUpSnackbarPreview() {
    SignupTheme {
        val coroutineScope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val snackbarMessage = stringResource(R.string.signup_success)

        Surface {
            Column {
                Button(
                    onClick = {
                        coroutineScope.launch {
                            snackbarHostState.showSnackbar(snackbarMessage)
                        }
                    },
                    modifier = Modifier.padding(16.dp)
                ) {
                    Text("Show Snackbar")
                }

                SignUpSnackbar(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHostState = snackbarHostState // use same state
                )
            }
        }
    }
}