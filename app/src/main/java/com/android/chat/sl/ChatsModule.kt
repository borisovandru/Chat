package com.android.chat.sl

import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.chats.ChatsCommunication
import com.android.chat.ui.chats.ChatsViewModel

class ChatsModule(
    private val coreModule: CoreModule
) : BaseModule<ChatsViewModel> {
    override fun viewModel(): ChatsViewModel {
        return ChatsViewModel(ChatsCommunication.Base())
    }
}