package com.example.fizzbuzz.domain.repository

import com.example.fizzbuzz.domain.Result
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseUser
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val currentUser: FirebaseUser?
    fun signIn(email: String, password: String): Flow<Result<AuthResult>>
    fun signUp(username: String, email: String, password: String): Flow<Result<AuthResult>>
    fun signOut(): Flow<Result<Unit>>
}