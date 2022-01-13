package com.android.chat.sl.core

import com.android.chat.data.chat.BaseMessagesDataMapper
import com.android.chat.data.chat.ChatRepository
import com.android.chat.data.chat.UserId
import com.android.chat.domain.chat.ChatInteractor
import com.android.chat.ui.chat.BaseMessagesDomainToUiMapper
import com.android.chat.ui.chat.ChatCommunication
import com.android.chat.ui.chat.ChatViewModel
import com.android.chat.ui.chat.MessageDomainToUiMapper

class ChatModule(private val coreModule: CoreModule) : BaseModule<ChatViewModel> {
    override fun viewModel(): ChatViewModel {
        return ChatViewModel(
            ChatCommunication.Base(),
            ChatInteractor.Base(
                ChatRepository.Base(
                    coreModule.firebaseDatabaseProvider(),
                    UserId(coreModule.provideSharedPreferences())
                ),
                BaseMessagesDataMapper()
            ),
            BaseMessagesDomainToUiMapper(MessageDomainToUiMapper.Base())
        )
    }
}