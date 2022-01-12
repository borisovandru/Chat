package com.android.chat.ui.search

import com.android.chat.data.search.SearchData

interface SearchResultsMapper : SearchData.SearchMapper<SearchUserUi> {

    class Base : SearchResultsMapper {

        override fun map(id: String, name: String, photoUrl: String): SearchUserUi {
            return SearchUserUi.Base(id, name, photoUrl)
        }
    }
}