package com.igdev.exampleapp.extensions

import android.util.Patterns.EMAIL_ADDRESS
import android.widget.EditText
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputLayout
import com.igdev.exampleapp.Constants

fun EditText.setRequiredValidation(
    textInputLayout: TextInputLayout,
    isRequiredAtBegin: Boolean,
    isEmailValidation: Boolean = false) {
    if (isRequiredAtBegin && this.isNullOrEmpty()) {
        textInputLayout.error = Constants.Validation.REQUIRED_FIELD_TEXT
    }

    this.doOnTextChanged { text, _, _, _ ->
        if (text.isNullOrEmpty())
            textInputLayout.error = Constants.Validation.REQUIRED_FIELD_TEXT
        else if (isEmailValidation && !EMAIL_ADDRESS.matcher(text).matches())
            textInputLayout.error = Constants.Validation.INVALID_EMAIL_FORMAT
        else
            textInputLayout.error = ""
    }
}