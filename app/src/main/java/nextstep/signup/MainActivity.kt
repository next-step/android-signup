package nextstep.signup

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import kotlinx.coroutines.launch
import nextstep.signup.ui.component.SignupSnackBar
import nextstep.signup.ui.signup.SignupScreen
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                val scope = rememberCoroutineScope()
                val snackBarHostState = remember { SnackbarHostState() }

                Scaffold(
                    snackbarHost = { SignupSnackBar(snackBarHostState) },
                    containerColor = MaterialTheme.colorScheme.background,
                    modifier = Modifier.fillMaxSize(),
                ) { contentPadding ->
                    SignupScreen(
                        onSignupClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(
                                    message = getString(R.string.signup_success),
                                    duration = SnackbarDuration.Short,
                                )
                            }
                        },
                        modifier = Modifier.padding(contentPadding),
                    )
                }
            }
        }
    }
}
