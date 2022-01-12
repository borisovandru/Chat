package com.android.chat.ui.profile

import androidx.lifecycle.viewModelScope
import com.android.chat.data.profile.MyProfileData
import com.android.chat.data.profile.MyProfileRepository
import com.android.chat.ui.core.BaseViewModel
import kotlinx.coroutines.launch

class MyProfileViewModel(
    communication: MyProfileCommunication,
    private val repository: MyProfileRepository,
    private val mapper: MyProfileData.MyProfileMapper<MyProfileUi>,
) : BaseViewModel<MyProfileCommunication, MyProfileUi>(
    communication
) {

    fun init() = viewModelScope.launch {
        val data = repository.profile().map(mapper)
        communication.map(data)
    }

    fun signOut() = repository.signOut()
}