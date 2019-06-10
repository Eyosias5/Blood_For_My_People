package com.teambloodformypeople.util

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern


class PasswordValidator : TextWatcher {
    var isValid = false
        private set

    override fun afterTextChanged(editableText: Editable) {
        isValid = isValidPassword(editableText)
    }

    override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
    }

    override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
    }

    companion object {
        val EMAIL_PATTERN = Pattern.compile("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})")
        fun isValidPassword(password: CharSequence?): Boolean {
            return password != null && EMAIL_PATTERN.matcher(password).matches()
        }
    }
}