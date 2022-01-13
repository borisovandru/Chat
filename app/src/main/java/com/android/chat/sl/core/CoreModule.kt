package com.android.chat.sl.core

import android.content.Context
import android.content.SharedPreferences
import com.android.chat.core.FirebaseDatabaseProvider
import com.android.chat.ui.main.NavigationCommunication

interface CoreModule {

    fun provideSharedPreferences(): SharedPreferences
    fun firebaseDatabaseProvider(): FirebaseDatabaseProvider
    fun navigationCommunication(): NavigationCommunication

    class Base(private val context: Context) : CoreModule {
        private val firebaseDatabaseProvider = FirebaseDatabaseProvider.Base()
        private val navigationCommunication = NavigationCommunication.Base()

        override fun provideSharedPreferences(): SharedPreferences =
            context.getSharedPreferences("ForceAppSharedPref", Context.MODE_PRIVATE)

        override fun firebaseDatabaseProvider() = firebaseDatabaseProvider

        override fun navigationCommunication() = navigationCommunication
    }
}