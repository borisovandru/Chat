package com.android.chat.sl.core

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.android.chat.ui.chat.ChatViewModel
import com.android.chat.ui.chats.ChatsViewModel
import com.android.chat.ui.groups.create.CreateGroupViewModel
import com.android.chat.ui.login.LoginViewModel
import com.android.chat.ui.main.MainViewModel
import com.android.chat.ui.profile.MyProfileViewModel
import com.android.chat.ui.search.SearchViewModel

class ViewModelsFactory(
    private val dependencyContainer: DependencyContainer,
) : ViewModelProvider.Factory {

    private val map = HashMap<Class<*>, Feature>().apply {
        put(LoginViewModel::class.java, Feature.LOGIN)
        put(MainViewModel::class.java, Feature.MAIN)
        put(SearchViewModel::class.java, Feature.SEARCH)
        put(MyProfileViewModel::class.java, Feature.MY_PROFILE)
        put(ChatsViewModel::class.java, Feature.CHATS)
        put(ChatViewModel::class.java, Feature.CHAT)
        put(CreateGroupViewModel::class.java, Feature.CREATE_GROUP)
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val feature =
            map[modelClass] ?: throw IllegalStateException("unknown viewModel $modelClass")
        return dependencyContainer.module(feature).viewModel() as T
    }
}
