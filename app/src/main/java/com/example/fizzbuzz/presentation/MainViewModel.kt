package com.example.fizzbuzz.presentation

import androidx.lifecycle.ViewModel
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {
    val currentUser: FirebaseUser?
        get() = authRepository.currentUser
}