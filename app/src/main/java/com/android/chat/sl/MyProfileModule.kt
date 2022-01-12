package com.android.chat.sl

import com.android.chat.data.profile.MyProfileRepository
import com.android.chat.data.profile.MyProfileData
import com.android.chat.sl.core.BaseModule
import com.android.chat.ui.profile.MyProfileCommunication
import com.android.chat.ui.profile.MyProfileViewModel

class MyProfileModule : BaseModule<MyProfileViewModel> {

    override fun viewModel() = MyProfileViewModel(
        MyProfileCommunication.Base(),
        MyProfileRepository.Base(),
        MyProfileData.MyProfileMapper.Base()
    )
}