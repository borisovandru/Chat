package com.android.chat.data.login

import com.google.android.gms.tasks.Task
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine
import com.android.chat.core.FirebaseDatabaseProvider


interface LoginRepository {

    suspend fun saveUser(user: UserInitial)

    fun user(): Any?

    class Base(private val firebaseDatabaseProvider: FirebaseDatabaseProvider) : LoginRepository {
        override fun user() = Firebase.auth.currentUser
        override suspend fun saveUser(user: UserInitial) {
            val value = firebaseDatabaseProvider.provideDatabase()
                .child("users")
                .child(user()!!.uid)
                .setValue(user)
            handlerResult(value)
        }

        private suspend fun handlerResult(value: Task<Void>): Unit =
            suspendCoroutine { continuation ->
                value.addOnSuccessListener {
                    continuation.resume(Unit)
                }.addOnFailureListener {
                    continuation.resumeWithException(it)
                }
            }
    }
}
