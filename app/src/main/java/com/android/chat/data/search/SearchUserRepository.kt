package com.android.chat.data.search

import android.content.SharedPreferences
import com.android.chat.core.Save
import com.android.chat.data.login.UserInitial
import com.google.firebase.database.*
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine
import com.android.chat.core.FirebaseDatabaseProvider

interface SearchUserRepository : Save<String> {

    suspend fun search(query: String): List<SearchData>

    class Base
        (
        private val firebaseDatabaseProvider: FirebaseDatabaseProvider,
        private val userIdContainer: Save<String>
    ) :
        SearchUserRepository {

        override fun save(data: String) = userIdContainer.save(data)

        override suspend fun search(query: String): List<SearchData> {
            val users = firebaseDatabaseProvider.provideDatabase()
                .child("users")
                .orderByChild("login")
                .equalTo(query)
            return handleResult(users).map { (key, data) ->
                SearchData.Base(
                    key,
                    data.name,
                    data.photoUrl
                )
            }
        }

        private suspend fun handleResult(users: Query) =
            suspendCoroutine<List<Pair<String, UserInitial>>> { cont ->
                users.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) =
                        snapshot.children.mapNotNull {
                            Pair(it.key!!, it.getValue(UserInitial::class.java)!!)
                        }.let { cont.resume(it) }

                    override fun onCancelled(error: DatabaseError) = Unit
                })
            }
    }
}