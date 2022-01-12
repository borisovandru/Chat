package com.android.chat.ui.search

import com.android.chat.ui.core.Communication

interface SearchCommunication : Communication<SearchUserListUi> {
    class Base : Communication.Base<SearchUserListUi>(), SearchCommunication
}