package com.android.chat.sl.core

import com.android.chat.sl.login.LoginModule

interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule) : DependencyContainer {

        override fun module(feature: Feature) = when (feature) {
            Feature.LOGIN -> LoginModule(coreModule)
            else -> throw IllegalStateException("unknown feature $feature")
        }
    }
}