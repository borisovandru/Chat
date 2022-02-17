package com.android.chat.ui.groups.create

import androidx.lifecycle.viewModelScope
import com.android.chat.data.groups.create.CreateGroupRepository
import com.android.chat.ui.core.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CreateGroupViewModel(
    communication: CreateGroupCommunication,
    private val repository: CreateGroupRepository
) : BaseViewModel<CreateGroupCommunication, CreateGroupUi>(communication) {

    fun createGroup(name: String) {
        if (name.trim().isEmpty()) return
        viewModelScope.launch(Dispatchers.IO) {
            if (repository.createGroup(name.trim()))
                init()
            //todo else handle fail globally
        }
    }

    fun init() {
        viewModelScope.launch(Dispatchers.IO) {
            val groups = repository.groups()
            withContext(Dispatchers.Main) { communication.map(CreateGroupUi.Base(groups)) }
        }
    }
}