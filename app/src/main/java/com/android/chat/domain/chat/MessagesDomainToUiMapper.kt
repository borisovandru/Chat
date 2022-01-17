package com.android.chat.domain.chat

import com.android.chat.core.Abstract

interface MessagesDomainToUiMapper<T> : Abstract.Mapper.DomainToUi<List<MessageDomain>, T>
