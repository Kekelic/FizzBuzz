package com.example.fizzbuzz.exceptions

import androidx.annotation.StringRes
import com.example.fizzbuzz.R

class AuthErrorException(errorCode: String) {

    @StringRes
    var emailErrorMessage = when (errorCode) {
        "ERROR_INVALID_EMAIL" -> R.string.error_invalid_email
        "ERROR_EMAIL_ALREADY_IN_USE" -> R.string.error_email_already_in_use
        "ERROR_USER_NOT_FOUND" -> R.string.error_user_not_found
        else -> null
    }

    @StringRes
    var passwordErrorMessage = when (errorCode) {
        "ERROR_WRONG_PASSWORD" -> R.string.error_wrong_password
        "ERROR_WEAK_PASSWORD" -> R.string.error_weak_password
        "INVALID_LOGIN_CREDENTIALS" -> R.string.error_invalid_credentials
        else -> null
    }

    @StringRes
    var specificErrorMessage =
        if (emailErrorMessage == null && passwordErrorMessage == null) {
            when (errorCode) {
                "ERROR_USER_DISABLED" -> R.string.error_user_disabled
                "ERROR_USER_TOKEN_EXPIRED" -> R.string.error_user_token_expired
                else -> R.string.error_something_went_wrong
            }
        } else null
}