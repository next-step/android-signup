package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val inputFields: InputFields = remember { InputFields() }
            val snackBarHostState = remember { SnackbarHostState() }
            val scope = rememberCoroutineScope()

            SignupTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = MaterialTheme.colorScheme.background,
                    snackbarHost = {
                        val height = LocalConfiguration.current.screenHeightDp
                        SnackbarHost(
                            snackBarHostState,
                            modifier = Modifier.padding(bottom = (height - 148).dp)
                        )
                    },
                ) { paddingValues ->
                    SignUpScreen(
                        inputFields = inputFields,
                        onSignUpButtonClick = {
                            scope.launch {
                                snackBarHostState.showSnackbar(getString(R.string.sign_up_sign_up_done))
                            }
                        },
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(paddingValues)
                    )
                }
            }
        }
    }
}
