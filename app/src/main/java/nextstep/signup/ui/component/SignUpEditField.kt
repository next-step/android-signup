package nextstep.signup.ui.component

import androidx.annotation.VisibleForTesting
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import nextstep.signup.ui.model.SignUpInputModel
import nextstep.signup.ui.model.SignUpInputType
import nextstep.signup.ui.theme.SignupTheme
import nextstep.signup.ui.theme.Typography

@VisibleForTesting
@Composable
private fun SignUpEditField(
    modifier: Modifier = Modifier,
    value: String,
    type: SignUpInputType,
    onValueChanged: (String) -> Unit,
) {
    val isValid = if (value.isNotEmpty()) type.isValid(value) else true
    val errorMessage = type.errorMessageResId(value).let { if (it > 0) stringResource(it) else "" }

    TextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        textStyle = Typography.bodyLarge,
        singleLine = true,
        onValueChange = onValueChanged,
        isError = !isValid,
        supportingText = {
            if (!isValid) {
                Text(
                    text = errorMessage,
                    style = Typography.bodySmall,
                    color = MaterialTheme.colorScheme.error,
                )
            }
        },
        label = { Text(text = stringResource(type.labelResId)) },
        visualTransformation = if (type.keyboardType == KeyboardType.Password) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = KeyboardOptions(
            keyboardType = type.keyboardType,
            imeAction = if (type.hasNextField()) ImeAction.Next else ImeAction.Done
        )
    )
}

@Composable
fun SignUpEditFields(
    modifier: Modifier = Modifier,
    inputModel: SignUpInputModel,
    onUpdateModel: (SignUpInputModel) -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(36.dp),
    ) {
        SignUpEditField(
            value = inputModel.username,
            type = SignUpInputType.USERNAME,
            onValueChanged = { onUpdateModel(inputModel.copy(username = it)) },
        )
        SignUpEditField(
            value = inputModel.email,
            type = SignUpInputType.EMAIL,
            onValueChanged = { onUpdateModel(inputModel.copy(email = it)) },
        )
        SignUpEditField(
            value = inputModel.password,
            type = SignUpInputType.PASSWORD,
            onValueChanged = { onUpdateModel(inputModel.copy(password = it)) },
        )
        SignUpEditField(
            value = inputModel.passwordConfirm,
            type = SignUpInputType.PASSWORD_CONFIRM,
            onValueChanged = { onUpdateModel(inputModel.copy(passwordConfirm = it)) },
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
            var inputModel by remember { mutableStateOf(SignUpInputModel()) }

            SignUpEditFields(
                inputModel = inputModel,
                onUpdateModel = { inputModel = it }
            )
        }
    }
}