package com.android.chat.sl.core

import android.content.Context
import android.content.SharedPreferences
import com.android.chat.core.FirebaseDatabaseProvider

interface CoreModule {

    fun provideSharedPreferences(): SharedPreferences
    fun firebaseDatabaseProvider(): FirebaseDatabaseProvider

    class Base(private val context: Context) : CoreModule {
        private val firebaseDatabaseProvider = FirebaseDatabaseProvider.Base()
        override fun provideSharedPreferences(): SharedPreferences =
            context.getSharedPreferences("ChatAppSharedPref", Context.MODE_PRIVATE)

        override fun firebaseDatabaseProvider() = firebaseDatabaseProvider
    }
}