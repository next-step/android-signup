package nextstep.signup

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import nextstep.signup.domain.Email
import nextstep.signup.domain.Password
import nextstep.signup.domain.PasswordConfirm
import nextstep.signup.domain.Username
import nextstep.signup.mapper.toUiState
import nextstep.signup.state.InputFieldState

class InputFields {
    var fields: Map<InputFieldKey, InputFieldState> by mutableStateOf(
        mapOf(
            InputFieldKey.USERNAME to Username().toUiState(),
            InputFieldKey.EMAIL to Email().toUiState(),
            InputFieldKey.PASSWORD to Password().toUiState(),
            InputFieldKey.PASSWORD_CONFIRM to PasswordConfirm().toUiState()
        )
    )
        private set

    private val fieldChangeListeners: Map<InputFieldKey, InputFieldChangeListener> =
        mapOf(
            InputFieldKey.USERNAME to InputFieldChangeListener { username: String ->
                fields += InputFieldKey.USERNAME to Username(username).toUiState()
            },
            InputFieldKey.EMAIL to InputFieldChangeListener { it: String ->
                fields += InputFieldKey.EMAIL to Email(it).toUiState()
            },
            InputFieldKey.PASSWORD to InputFieldChangeListener { it: String ->
                fields += mapOf(
                    InputFieldKey.PASSWORD to Password(it).toUiState(),
                    InputFieldKey.PASSWORD_CONFIRM to PasswordConfirm(
                        password = it,
                        value = fields[InputFieldKey.PASSWORD_CONFIRM]?.input ?: ""
                    ).toUiState()
                )
            },
            InputFieldKey.PASSWORD_CONFIRM to InputFieldChangeListener { it: String ->
                fields += InputFieldKey.PASSWORD_CONFIRM to
                        PasswordConfirm(
                            password = fields[InputFieldKey.PASSWORD]?.input ?: "",
                            value = it
                        ).toUiState()
            },
        )

    fun update(key: InputFieldKey, newValue: String) {
        fieldChangeListeners[key]?.onChanged(newValue)
    }

    fun isValidAll(): Boolean {
        return fields.values.all { it.isError.not() && it.input.isNotEmpty() }
    }
}
