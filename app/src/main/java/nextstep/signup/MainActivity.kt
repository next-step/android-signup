package nextstep.signup

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import nextstep.signup.ui.signup.SignUpScreen
import nextstep.signup.ui.theme.SignupTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SignupTheme {
                SignUpScreen()
            }
        }
    }
}
