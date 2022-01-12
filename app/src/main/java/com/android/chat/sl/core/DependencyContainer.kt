package com.android.chat.sl.core

import com.android.chat.sl.LoginModule
import com.android.chat.sl.MainModule
import com.android.chat.sl.MyProfileModule
import com.android.chat.sl.SearchModule

interface DependencyContainer {
    fun module(feature: Feature): BaseModule<*>
    class Base(private val coreModule: CoreModule) : DependencyContainer {

        override fun module(feature: Feature) = when (feature) {
            Feature.LOGIN -> LoginModule()
            Feature.MAIN -> MainModule()
            Feature.SEARCH -> SearchModule()
            Feature.MY_PROFILE -> MyProfileModule()
            else -> throw IllegalStateException("unknown feature $feature")
        }
    }
}