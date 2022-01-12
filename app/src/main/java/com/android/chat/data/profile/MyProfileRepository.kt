package com.android.chat.data.profile

import com.android.chat.data.login.UserInitial
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.ktx.Firebase
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.android.chat.core.FirebaseDatabaseProvider


interface MyProfileRepository {

    suspend fun profile(): MyProfileData

    fun signOut()

    class Base(private val firebaseDatabaseProvider: FirebaseDatabaseProvider) :
        MyProfileRepository {
        override suspend fun profile(): MyProfileData {
            val user = firebaseDatabaseProvider.provideDatabase().child("users")
                .child(Firebase.auth.currentUser!!.uid)
            return MyProfileData.Base(handleResult(user))
        }

        override fun signOut() = Firebase.auth.signOut()

        private suspend fun handleResult(reference: DatabaseReference) =
            suspendCoroutine<UserInitial> { cont ->
                reference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        cont.resume(snapshot.getValue(UserInitial::class.java)!!)
                    }

                    override fun onCancelled(error: DatabaseError) = Unit
                })
            }
    }
}