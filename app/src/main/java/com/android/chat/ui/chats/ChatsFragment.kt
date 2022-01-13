package com.android.chat.ui.chats

import com.android.chat.R
import com.android.chat.sl.core.Feature
import com.android.chat.ui.main.BaseFragment

class ChatsFragment : BaseFragment<ChatsViewModel>() {
    override fun viewModelClass() = ChatsViewModel::class.java
    override val titleResId = R.string.title_chats
    override fun name() = Feature.CHATS.name


    //todo do later -  list of chats

}