package com.android.chat.sl.core

import android.content.Context
import android.content.SharedPreferences
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener

interface CoreModule {

    fun provideSharedPreferences(): SharedPreferences

    class Base(private val context: Context) : CoreModule {
        override fun provideSharedPreferences(): SharedPreferences =
            context.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)
    }
}

const val DATABASE_URL = "https://forcepush-acb5b-default-rtdb.europe-west1.firebasedatabase.app"

class EmptyDataListener : ValueEventListener {
    override fun onDataChange(snapshot: DataSnapshot) = Unit
    override fun onCancelled(error: DatabaseError) = Unit
}