package com.android.chat.ui.groups.create

import com.android.chat.core.Abstract

interface CreateGroupUi : Abstract.UiObject,
    Abstract.Object<Unit, Abstract.Mapper.Data<List<String>, Unit>> {

    class Base(private val groups: List<String>) : CreateGroupUi {
        override fun map(mapper: Abstract.Mapper.Data<List<String>, Unit>) {
            mapper.map(groups)
        }
    }
}