package com.android.chat.data.search

import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.android.chat.data.login.UserInitial
import com.android.chat.sl.core.DATABASE_URL
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

interface SearchUserRepository {

    suspend fun search(query: String): List<SearchData>

    class Base : SearchUserRepository {

        override suspend fun search(query: String): List<SearchData> {
            val users =
                Firebase.database(DATABASE_URL).reference.root.child("users").orderByChild("login")
                    .equalTo(query)
            return handleResult(users).map {
                SearchData.Base(it.login,
                    it.name,
                    it.photoUrl
                )
            }
        }

        private suspend fun handleResult(users: Query) =
            suspendCoroutine<List<UserInitial>> { cont ->
                users.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) =
                        snapshot.children.mapNotNull {
                            it.getValue(UserInitial::class.java)//todo key as uid
                        }.let { cont.resume(it) }

                    override fun onCancelled(error: DatabaseError) = Unit
                })
            }
    }
}