package com.zzh.chatroom0.domain.model

data class Message(
    val text: String,
    val formattedTime: String,
    val username: String,
)

