package com.android.chat.ui.groups.create

import com.android.chat.ui.core.Communication

interface CreateGroupCommunication : Communication<CreateGroupUi> {
    class Base : Communication.Base<CreateGroupUi>(), CreateGroupCommunication
}