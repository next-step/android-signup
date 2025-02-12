package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import nextstep.signup.ui.component.SignUpSnackBar
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                val snackBarHostState = remember { SnackbarHostState() }
                var showSnackBar by remember { mutableStateOf(false) }

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
                ) { innerPadding ->

                    MainScreen(
                        modifier = Modifier.padding(innerPadding),
                        onShowSnackBar = { showSnackBar = true }
                    )
                    if (showSnackBar) SignUpSnackBar(
                        snackBarHostState = snackBarHostState,
                        onDismissSnackBar = { showSnackBar = false }
                    )
                }
            }
        }
    }
}
