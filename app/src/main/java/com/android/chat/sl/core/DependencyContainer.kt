package com.android.chat.sl.core

import com.android.chat.sl.*

interface DependencyContainer {

    fun module(feature: Feature): BaseModule<*>

    class Base(private val coreModule: CoreModule) : DependencyContainer {

        override fun module(feature: Feature) = when (feature) {
            Feature.LOGIN -> LoginModule(coreModule)
            Feature.MAIN -> MainModule(coreModule)
            Feature.SEARCH -> SearchModule(coreModule)
            Feature.MY_PROFILE -> MyProfileModule(coreModule)
            Feature.CHATS -> ChatsModule(coreModule)
            Feature.CHAT -> ChatModule(coreModule)
            Feature.CREATE_GROUP -> CreateGroupModule(coreModule)
        }
    }
}