package com.android.chat.sl

import com.android.chat.data.search.GroupId
import com.android.chat.data.chat.UserId
import com.android.chat.data.search.SearchGroupRepository
import com.android.chat.data.search.SearchUserRepository
import com.android.chat.domain.SearchInteractor
import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.search.SearchCommunication
import com.android.chat.ui.search.SearchResultsMapper
import com.android.chat.ui.search.SearchViewModel

class SearchModule(private val coreModule: CoreModule) : BaseModule<SearchViewModel> {
    override fun viewModel() = SearchViewModel(
        SearchCommunication.Base(),
        SearchResultsMapper.Base(),
        SearchInteractor.Base(
            SearchUserRepository.Base(
                coreModule.firebaseDatabaseProvider(),
                UserId(coreModule.provideSharedPreferences())
            ),
            SearchGroupRepository.Base(
                coreModule.firebaseDatabaseProvider(),
                GroupId(coreModule.provideSharedPreferences())
            )
        ),
        coreModule.navigationCommunication()
    )
}