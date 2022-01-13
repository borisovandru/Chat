package com.android.chat.ui.main

import com.android.chat.R
import com.android.chat.ui.chat.ChatFragment
import com.android.chat.ui.chats.ChatsFragment
import com.android.chat.ui.core.BaseViewModel
import com.android.chat.ui.profile.MyProfileFragment
import com.android.chat.ui.search.SearchFragment

class MainViewModel(
    communication: NavigationCommunication,
) : BaseViewModel<NavigationCommunication, NavigationUi>(communication) {

    fun changeScreen(menuItemId: Int) {
        //todo save screen id
        communication.map(NavigationUi(menuItemId))
    }

    private val idMap = mapOf(
        R.id.navigation_profile to MyProfileFragment::class.java,
        R.id.navigation_search to SearchFragment::class.java,
        R.id.navigation_chats to ChatsFragment::class.java,
        R.id.chat_screen to ChatFragment::class.java
    )//todo move to some class

    fun getFragment(id: Int): BaseFragment<*> {
        val clazz = idMap[id] ?: throw IllegalStateException("unknown id $id")
        return clazz.newInstance()
    }

    fun init() {
        changeScreen(R.id.navigation_profile) //todo get from navigation cache
    }
}