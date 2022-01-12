package com.android.chat.data.profile

import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.android.chat.data.login.UserInitial
import com.android.chat.sl.core.DATABASE_URL
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

interface MyProfileRepository {

    suspend fun profile(): MyProfileData

    fun signOut()

    class Base : MyProfileRepository {
        override suspend fun profile(): MyProfileData {
            val user = Firebase.database(DATABASE_URL).reference.root.child("users")
                .child(Firebase.auth.currentUser!!.uid)
            val userInitial = handleResult(user)
            return MyProfileData.Base(userInitial)
        }

        override fun signOut() = Firebase.auth.signOut()

        private suspend fun handleResult(reference: DatabaseReference) =
            suspendCoroutine<UserInitial> { cont ->
                reference.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        cont.resume(snapshot.getValue(UserInitial::class.java)!!)
                    }

                    override fun onCancelled(error: DatabaseError) {
                        cont.resumeWithException(error.toException())
                    }
                })
            }
    }
}