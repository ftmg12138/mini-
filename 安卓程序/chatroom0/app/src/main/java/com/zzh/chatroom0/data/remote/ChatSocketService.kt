package com.zzh.chatroom0.data.remote

import com.zzh.chatroom0.domain.model.Message
import com.zzh.chatroom0.util.Resource
import kotlinx.coroutines.flow.Flow

interface ChatSocketService {

    suspend fun initSession(
        username: String
    ):Resource<Unit>

    suspend fun sendMessage(message: String)

    fun observeMessages(): Flow<Message>

    suspend fun closeSession()

    companion object {
        const val BASE_URL = "ws://10.21.205.202:8080/"
    }

    sealed class Endpoints(val url: String) {//
        object ChatSocket : Endpoints("$BASE_URL/chat-socket")
    }
}