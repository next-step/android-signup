package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import nextstep.signup.ui.component.SignupScreen
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen() {
    SignupTheme {
        val scope = rememberCoroutineScope()
        val snackbarHostState = remember { SnackbarHostState() }
        val snackbarMessage = stringResource(R.string.signup_complete_snackbar_message)

        Scaffold(
            snackbarHost = { SnackbarHost(snackbarHostState) }
        ) { innerPadding ->
            SignupScreen(
                onSignupComplete = {
                    scope.launch {
                        snackbarHostState.showSnackbar(snackbarMessage)
                    }
                },
                modifier = Modifier.padding(innerPadding)
            )
        }
    }
}

@Preview(
    showBackground = true,
    widthDp = 360,
    heightDp = 640,
)
@Composable
fun SignupScreenPreview() {
    MainScreen()
}
