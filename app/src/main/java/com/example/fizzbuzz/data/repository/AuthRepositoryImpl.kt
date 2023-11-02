package com.example.fizzbuzz.data.repository

import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.google.firebase.FirebaseException
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class AuthRepositoryImpl @Inject constructor(
    private val firebaseAuth: FirebaseAuth,
    private val firebaseFirestore: FirebaseFirestore
) : AuthRepository {

    override val currentUser: FirebaseUser?
        get() = firebaseAuth.currentUser

    override fun signIn(email: String, password: String): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading())
            val result = firebaseAuth.signInWithEmailAndPassword(email, password).await()
            emit(Result.Success(result))
        }.catch {
            when (it) {
                is FirebaseAuthException -> {
                    emit(Result.Error(message = it.message.toString(), errorCode = it.errorCode))
                }

                is FirebaseException -> {
                    emit(
                        Result.Error(
                            it.message.toString(),
                            errorCode = "INVALID_LOGIN_CREDENTIALS"
                        )
                    )
                }

                else -> {
                    emit(Result.Error(it.message.toString()))
                }
            }
        }
    }

    override fun signUp(
        username: String,
        email: String,
        password: String
    ): Flow<Result<AuthResult>> {
        return flow {
            emit(Result.Loading())
            val result = firebaseAuth.createUserWithEmailAndPassword(email, password).await()
            val newUser = hashMapOf(
                "username" to username,
            )
            firebaseFirestore.collection("Users").document(currentUser!!.uid).set(newUser).await()
            emit(Result.Success(result))
        }.catch {
            if (it is FirebaseAuthException) {
                emit(Result.Error(message = it.message.toString(), errorCode = it.errorCode))
            } else {
                emit(Result.Error(it.message.toString()))
            }

        }

    }

    override fun signOut(): Flow<Result<Unit>> {
        return flow {
            emit(Result.Loading())
            firebaseAuth.signOut()
            emit(Result.Success(Unit))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }


}