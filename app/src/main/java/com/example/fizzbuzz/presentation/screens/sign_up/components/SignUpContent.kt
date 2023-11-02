package com.example.fizzbuzz.presentation.screens.sign_up.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.fizzbuzz.R
import com.example.fizzbuzz.presentation.screens.components.CustomButtonProgressIndicator
import com.example.fizzbuzz.presentation.screens.sign_up.SignUpScreenIntent
import com.example.fizzbuzz.presentation.screens.sign_up.SignUpScreenState
import com.example.fizzbuzz.ui.theme.FizzBuzzTheme
import com.example.fizzbuzz.ui.theme.PressStart2P

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpContent(
    state: SignUpScreenState,
    onIntent: (SignUpScreenIntent) -> Unit,
    navigateToSignInScreen: () -> Unit,
    modifier: Modifier = Modifier
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(40.dp))
        Text(
            text = stringResource(id = R.string.app_name),
            fontSize = 34.sp,
            fontFamily = PressStart2P,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = stringResource(id = R.string.sign_up_to_start_the_game),
            fontWeight = FontWeight.Medium,
            fontSize = 16.sp,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(20.dp))
        OutlinedTextField(
            value = state.username,
            onValueChange = {
                onIntent(SignUpScreenIntent.SetUsername(it))
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            label = {
                Text(text = stringResource(id = R.string.username))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text
            ),
            isError = state.usernameErrorMessage != null,
            supportingText = {
                state.usernameErrorMessage?.let {
                    Text(text = stringResource(id = it))
                }
            },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.email,
            onValueChange = {
                onIntent(SignUpScreenIntent.SetEmail(it))

            },
            modifier = Modifier.fillMaxWidth(0.7f),
            label = {
                Text(text = stringResource(id = R.string.email))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email
            ),
            isError = state.emailErrorMessage != null,
            supportingText = {
                state.emailErrorMessage?.let {
                    Text(text = stringResource(id = it))
                }
            },
            shape = RoundedCornerShape(8.dp),
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = state.password,
            onValueChange = {
                onIntent(SignUpScreenIntent.SetPassword(it))
            },
            modifier = Modifier.fillMaxWidth(0.7f),
            label = {
                Text(text = stringResource(id = R.string.password))
            },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            isError = state.passwordErrorMessage != null,
            supportingText = {
                state.passwordErrorMessage?.let {
                    Text(text = stringResource(id = it))
                }
            },
            shape = RoundedCornerShape(8.dp),
            visualTransformation = if (state.isPasswordVisible) {
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            },
            trailingIcon = {
                val imageVector = if (state.isPasswordVisible) {
                    Icons.Filled.Visibility
                } else {
                    Icons.Filled.VisibilityOff
                }
                IconButton(
                    onClick = {
                        onIntent(SignUpScreenIntent.SwitchPasswordVisibility)
                    }
                ) {
                    Icon(
                        imageVector = imageVector,
                        contentDescription = stringResource(id = R.string.password_visibility)
                    )
                }

            },
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(
            onClick = {
                onIntent(SignUpScreenIntent.SignUp)
            },
            modifier = Modifier
                .fillMaxWidth(0.5f),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black,
            ),
            shape = RoundedCornerShape(15.dp)
        ) {
            if (state.isSignUpLoading) {
                CustomButtonProgressIndicator()
            } else {
                Text(
                    text = stringResource(id = R.string.sign_up),
                    modifier = Modifier.padding(7.dp)
                )
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextButton(onClick = { navigateToSignInScreen() }) {
            Text(
                text = stringResource(id = R.string.already_have_account),
            )
        }
        Spacer(modifier = Modifier.height(40.dp))

    }

}

@Preview(showBackground = true)
@Composable
fun SignUpContentPreview() {
    FizzBuzzTheme {
        SignUpContent(
            state = SignUpScreenState(),
            onIntent = {},
            navigateToSignInScreen = {}
        )
    }
}