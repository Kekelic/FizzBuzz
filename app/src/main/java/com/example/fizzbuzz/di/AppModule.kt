package com.example.fizzbuzz.di

import android.content.Context
import androidx.room.Room
import com.example.fizzbuzz.data.dao.HistoryScoreDao
import com.example.fizzbuzz.data.database.LocalHistoryDb
import com.example.fizzbuzz.data.repository.AuthRepositoryImpl
import com.example.fizzbuzz.data.repository.FirestoreRepositoryImpl
import com.example.fizzbuzz.data.repository.LocalRepositoryImpl
import com.example.fizzbuzz.domain.repository.AuthRepository
import com.example.fizzbuzz.domain.repository.FirestoreRepository
import com.example.fizzbuzz.domain.repository.LocalRepository
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideAuthRepositoryImpl(): AuthRepository = AuthRepositoryImpl(
        firebaseAuth = Firebase.auth,
        firebaseFirestore = Firebase.firestore
    )

    @Provides
    @Singleton
    fun provideFirestoreRepositoryImpl(): FirestoreRepository = FirestoreRepositoryImpl(
        firebaseFirestore = Firebase.firestore
    )

    @Provides
    @Singleton
    fun provideLocalHistoryDb(
        @ApplicationContext context: Context
    ) = Room.databaseBuilder(
        context, LocalHistoryDb::class.java, "history_score"
    ).build()

    @Provides
    @Singleton
    fun provideHistoryScoreDao(
        localHistoryDb: LocalHistoryDb
    ) = localHistoryDb.historyScoreDao

    @Provides
    @Singleton
    fun provideLocalHistoryRepository(
        historyScoreDao: HistoryScoreDao
    ): LocalRepository = LocalRepositoryImpl(
        historyScoreDao = historyScoreDao
    )

}