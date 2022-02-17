package com.android.chat.ui.search

import com.android.chat.ui.core.Communication

interface SearchCommunication : Communication<SearchResultListUi> {
    class Base : Communication.Base<SearchResultListUi>(), SearchCommunication
}