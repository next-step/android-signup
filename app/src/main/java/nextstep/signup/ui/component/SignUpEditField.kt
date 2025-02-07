package nextstep.signup.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.R
import nextstep.signup.ui.model.SignUpInputsModel
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.Typography

@Composable
private fun SignUpEditField(
    modifier: Modifier = Modifier,
    label: String,
    keyboardType: KeyboardType,
    onValueChanged: (String) -> Unit
) {
    var text by remember { mutableStateOf("") }
    LaunchedEffect(text) {
        onValueChanged(text)
    }

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = text,
        textStyle = Typography.bodyLarge,
        maxLines = 1,
        singleLine = true,
        onValueChange = { text = it },
        label = {
            Text(text = label)
        },
        visualTransformation = if (keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        )
    )
}

@Composable
fun SignUpEditFields(
    modifier: Modifier = Modifier,
    onUpdateModel: (SignUpInputsModel) -> Unit,
) {
    var model by remember { mutableStateOf(SignUpInputsModel()) }
    LaunchedEffect(model) {
        onUpdateModel(model)
    }
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        SignUpEditField(
            label = stringResource(R.string.username),
            keyboardType = KeyboardType.Text,
            onValueChanged = { model = model.copy(username = it) }
        )
        SignUpEditField(
            label = stringResource(R.string.email),
            keyboardType = KeyboardType.Email,
            onValueChanged = { model = model.copy(email = it) }
        )
        SignUpEditField(
            label = stringResource(R.string.password),
            keyboardType = KeyboardType.Password,
            onValueChanged = { model = model.copy(password = it) }
        )
        SignUpEditField(
            label = stringResource(R.string.password_confirm),
            keyboardType = KeyboardType.Password,
            onValueChanged = { model = model.copy(passwordConfirm = it) }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun SignUpEditFieldsPreview() {
    SignupTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 32.dp)
                .padding(top = 100.dp),
            color = MaterialTheme.colorScheme.background
        ) {
            var inputsModel by remember { mutableStateOf(SignUpInputsModel()) }

            SignUpEditFields(
                onUpdateModel = { inputsModel = it }
            )
        }
    }
}