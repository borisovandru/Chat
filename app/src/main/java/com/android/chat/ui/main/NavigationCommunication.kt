package com.android.chat.ui.main

import com.android.chat.ui.core.Communication

interface NavigationCommunication : Communication<NavigationUi> {
    class Base : Communication.Base<NavigationUi>(), NavigationCommunication
}