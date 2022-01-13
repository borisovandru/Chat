package com.android.chat.sl

import com.android.chat.data.chat.UserId
import com.android.chat.data.chats.ChatDataMapper
import com.android.chat.data.chats.ChatsRepository
import com.android.chat.data.chats.UserChatDataMapper
import com.android.chat.domain.chats.ChatDomainMapper
import com.android.chat.domain.chats.ChatsInteractor
import com.android.chat.domain.chats.UserChatDomainMapper
import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.chats.ChatsCommunication
import com.android.chat.ui.chats.ChatsViewModel

class ChatsModule(
    private val coreModule: CoreModule
) : BaseModule<ChatsViewModel> {
    override fun viewModel() = ChatsViewModel(
        ChatsCommunication.Base(),
        coreModule.navigationCommunication(),
        ChatsInteractor.Base(
            ChatsRepository.Base(
                coreModule.firebaseDatabaseProvider(),
                UserId(coreModule.provideSharedPreferences())
            ),
            ChatDataMapper.Base(),
            UserChatDataMapper.Base()
        ),
        ChatDomainMapper.Base(),
        UserChatDomainMapper.Base()
    )
}