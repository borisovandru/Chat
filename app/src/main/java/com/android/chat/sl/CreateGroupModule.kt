package com.android.chat.sl

import com.android.chat.data.groups.create.CreateGroupRepository
import com.android.chat.sl.core.BaseModule
import com.android.chat.sl.core.CoreModule
import com.android.chat.ui.groups.create.CreateGroupCommunication
import com.android.chat.ui.groups.create.CreateGroupViewModel

class CreateGroupModule(private val coreModule: CoreModule) : BaseModule<CreateGroupViewModel> {
    override fun viewModel() = CreateGroupViewModel(
        CreateGroupCommunication.Base(),
        CreateGroupRepository.Base(coreModule.firebaseDatabaseProvider())
    )
}