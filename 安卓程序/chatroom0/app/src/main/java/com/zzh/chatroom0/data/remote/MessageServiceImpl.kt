package com.zzh.chatroom0.data.remote

import com.zzh.chatroom0.data.remote.dto.MessageDto
import com.zzh.chatroom0.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.request.get
//这个文件是用来定义数据传输对象的，这个对象是用来在网络上传输的，所以需要序列化
class MessageServiceImpl(
    private val client: HttpClient
): MessageService {
    override suspend fun getAllMessages(): List<Message> {
        return try {//如果没有出现异常，就返回一个列表
            client.get<List<MessageDto>>(MessageService.Endpoints.GetAllMessages.url)
                .map { it.toMessage() }
        } catch (e: Exception) {//如果出现异常，就返回一个空的列表
            emptyList()
        }
    }
}