package com.android.chat.ui.chats

import androidx.lifecycle.viewModelScope
import com.android.chat.R
import com.android.chat.domain.chats.ChatDomain
import com.android.chat.domain.chats.ChatDomainMapper
import com.android.chat.domain.chats.ChatsInteractor
import com.android.chat.domain.chats.UserChatDomainMapper
import com.android.chat.ui.core.BaseViewModel
import com.android.chat.ui.main.NavigationCommunication
import com.android.chat.ui.main.NavigationUi
import com.android.chat.ui.search.Chat
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ChatsViewModel(
    communication: ChatsCommunication,
    private val navigation: NavigationCommunication,
    private val interactor: ChatsInteractor,
    private val mapper: ChatDomainMapper<ChatUi>,
    private val userMapper: UserChatDomainMapper<UserChatUi>,
    private val dispatchersIO: CoroutineDispatcher = Dispatchers.IO,
    private val dispatchersMain: CoroutineDispatcher = Dispatchers.Main,
) : BaseViewModel<ChatsCommunication, List<ChatUi>>(communication), Chat {

    private val chatsMap: MutableMap<String, ChatUi> = mutableMapOf()

    private val chatsRealtimeUpdateCallback = object : ChatsRealtimeUpdateCallback {
        override fun updateChats(chats: List<ChatDomain>) {
            viewModelScope.launch(dispatchersIO) {
                chats.forEach { handleChat(it) }
                val chatList = uiList()
                withContext(dispatchersMain) { communication.map(chatList) }
            }
        }
    }

    private fun handleChat(chatDomain: ChatDomain) {
        val chatUi: ChatUi = chatDomain.map(mapper)
        if (!chatsMap.containsValue(chatUi)) chatUi.put(chatsMap)
    }

    private suspend fun uiList() = chatsMap.map { (userId, chatUi) ->
        val userUi = interactor.userInfo(userId).map(userMapper)
        chatUi.aggregatedWith(userUi)
    }.sortedByDescending { it.sort() }

    override fun startChatWith(userId: String) {
        interactor.save(userId)
        navigation.map(NavigationUi(R.id.chat_screen))
    }

    fun startGettingUpdates() {
        interactor.startGettingUpdates(chatsRealtimeUpdateCallback)
    }

    fun stopGettingUpdates() {
        interactor.stopGettingUpdates()
    }
}

interface ChatsRealtimeUpdateCallback {
    fun updateChats(chats: List<ChatDomain>)

    object Empty : ChatsRealtimeUpdateCallback {
        override fun updateChats(chats: List<ChatDomain>) = Unit
    }
}