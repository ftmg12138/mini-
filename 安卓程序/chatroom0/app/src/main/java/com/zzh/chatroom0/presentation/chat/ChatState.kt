package com.zzh.chatroom0.presentation.chat

import com.zzh.chatroom0.domain.model.Message

data class ChatState(
    val messages: List<Message> = emptyList(),
    val isLoading : Boolean = false
)