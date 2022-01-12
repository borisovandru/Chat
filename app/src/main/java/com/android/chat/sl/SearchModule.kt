package com.android.chat.sl

import com.android.chat.data.search.SearchUserRepository
import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.search.SearchCommunication
import com.android.chat.ui.search.SearchResultsMapper
import com.android.chat.ui.search.SearchViewModel

class SearchModule : BaseModule<SearchViewModel> {
    override fun viewModel() = SearchViewModel(
        SearchCommunication.Base(),
        SearchResultsMapper.Base(),
        SearchUserRepository.Base()
    )
}