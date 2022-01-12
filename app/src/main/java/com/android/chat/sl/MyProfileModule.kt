package com.android.chat.sl

import com.android.chat.data.profile.MyProfileRepository
import com.android.chat.data.profile.MyProfileData
import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.profile.MyProfileCommunication
import com.android.chat.ui.profile.MyProfileViewModel
import com.android.chat.sl.core.CoreModule

class MyProfileModule(private val coreModule: CoreModule) : BaseModule<MyProfileViewModel> {

    override fun viewModel() = MyProfileViewModel(
        MyProfileCommunication.Base(),
        MyProfileRepository.Base(coreModule.firebaseDatabaseProvider()),
        MyProfileData.MyProfileMapper.Base()
    )
}