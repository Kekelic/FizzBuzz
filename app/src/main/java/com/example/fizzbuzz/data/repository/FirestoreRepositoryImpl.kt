package com.example.fizzbuzz.data.repository

import androidx.annotation.StringRes
import com.example.fizzbuzz.R
import com.example.fizzbuzz.domain.Result
import com.example.fizzbuzz.domain.model.Score
import com.example.fizzbuzz.domain.model.ScoreResponse
import com.example.fizzbuzz.domain.model.User
import com.example.fizzbuzz.domain.repository.FirestoreRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepositoryImpl @Inject constructor(
    private val firebaseFirestore: FirebaseFirestore
) : FirestoreRepository {

    override fun getUsername(Id: String): Flow<Result<String>> {
        return flow {
            emit(Result.Loading())
            val userSnapshot = firebaseFirestore.collection("Users").document(Id).get().await()
            val user = userSnapshot.toObject(User::class.java)
            emit(Result.Success("${user?.username}"))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }

    override fun saveScore(newScore: Score): Flow<Result<Int>> {
        return flow {
            emit(Result.Loading())
            val leaderboardLimit = 10
            @StringRes var resultMessage = R.string.empty_string

            //get all scores from leaderboard
            val scores = ArrayList<Score>()
            firebaseFirestore.collection("Leaderboard").orderBy("value", Query.Direction.DESCENDING)
                .get().await().map {
                    val score = it.toObject(Score::class.java)
                    score.id = it.id
                    scores.add(score)
                }

            //delete scores in leaderboard if there is more then leaderboard limit
            if (scores.size > leaderboardLimit) {
                for (i in 0..scores.size - leaderboardLimit) {
                    firebaseFirestore.collection("Leaderboard").document(scores[10 + i].id!!)
                        .delete().await()
                }
            }

            //get user's old score from leaderboard
            var oldScore: Score? = null
            firebaseFirestore.collection("Leaderboard").whereEqualTo("userID", newScore.userID)
                .get().await().map {
                    oldScore = it.toObject(Score::class.java)
                    oldScore?.id = it.id
                }

            if (oldScore != null) { //if user already has score in leaderboard
                if (oldScore!!.value!! < newScore.value!!) {
                    firebaseFirestore.collection("Leaderboard").document(oldScore!!.id!!)
                        .update("value", newScore.value).await()
                    resultMessage = R.string.you_got_new_score
                } else {
                    resultMessage = R.string.your_old_score_is_better
                }
            } else { //if user doesn't have score in leaderboard

                //hash map for storing new score
                val scoreHasMap = hashMapOf(
                    "value" to newScore.value,
                    "userID" to newScore.userID
                )

                if (scores.size < leaderboardLimit) {
                    firebaseFirestore.collection("Leaderboard").add(scoreHasMap).await()
                    resultMessage = R.string.your_score_is_added_to_leaderboard
                } else {
                    if (scores[leaderboardLimit - 1].value!! < newScore.value!!) {
                        firebaseFirestore.collection("Leaderboard")
                            .document(scores[leaderboardLimit - 1].id!!).delete().await()
                        firebaseFirestore.collection("Leaderboard").add(scoreHasMap).await()
                        resultMessage =
                            R.string.you_beat_user_from_top_10
                    } else {
                        resultMessage =
                            R.string.other_10_users_have_better_score
                    }
                }
            }

            emit(Result.Success(resultMessage))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }

    override fun getLeaderboard(): Flow<Result<List<ScoreResponse>>> {
        return flow {
            emit(Result.Loading())
            val scores = ArrayList<ScoreResponse>()
            firebaseFirestore.collection("Leaderboard")
                .orderBy("value", Query.Direction.DESCENDING).limit(10)
                .get().await().map {
                    val score = it.toObject(ScoreResponse::class.java)
                    score.id = it.id
                    scores.add(score)
                }
            for (score in scores) {
                val userSnapshot =
                    firebaseFirestore.collection("Users").document(score.userID!!).get().await()
                val user = userSnapshot.toObject(User::class.java)
                score.username = user?.username
            }
            emit(Result.Success(scores.toList()))
        }.catch {
            emit(Result.Error(it.message.toString()))
        }
    }


}