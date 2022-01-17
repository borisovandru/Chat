package com.android.chat.sl

import com.android.chat.data.search.SearchUserRepository
import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.search.SearchCommunication
import com.android.chat.ui.search.SearchResultsMapper
import com.android.chat.ui.search.SearchViewModel
import com.android.chat.sl.core.CoreModule
import com.android.chat.data.chat.UserId

class SearchModule(private val coreModule: CoreModule) : BaseModule<SearchViewModel> {
    override fun viewModel() = SearchViewModel(
        SearchCommunication.Base(),
        SearchResultsMapper.Base(),
        SearchUserRepository.Base(
            coreModule.firebaseDatabaseProvider(),
            UserId(coreModule.provideSharedPreferences())
        ),
        coreModule.navigationCommunication()
    )
}