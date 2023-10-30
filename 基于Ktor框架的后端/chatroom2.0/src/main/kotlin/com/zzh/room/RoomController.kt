package com.zzh.room

import com.zzh.data.MessageDataSource
import com.zzh.data.model.Message
import io.ktor.http.cio.websocket.*
import io.ktor.websocket.*
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.util.concurrent.ConcurrentHashMap

class RoomController(
    private val messagerDatasource: MessageDataSource
) {
    private val members = ConcurrentHashMap<String, Member>()//存储用户
    //加入房间
    fun onJoin(
        username: String,
        sessionId: String,
        socket: WebSocketSession
    ) {
        if(members.containsKey(username)) {//如果已经存在该用户
            throw MemberAlreadyExistsException()
        }
        members[username] = Member(//加入房间
            username = username,
            sessionId = sessionId,
            socket = socket
        )
    }
    //发送消息
    suspend fun sendMessage(senderUsername: String, message: String) {
        val messageEntity = Message(//创建消息实体
            text = message,
            username = senderUsername,
            timestamp = System.currentTimeMillis()
        )
        messagerDatasource.insertMessage(messageEntity)//插入消息
        members.values.forEach { member ->
            val parsedMessage = Json.encodeToString(messageEntity)
            member.socket.send(Frame.Text(parsedMessage))//发送消息
        }
    }
    //获取所有消息
    suspend fun getAllMessages(): List<Message> {
        return messagerDatasource.getAllMessages()
    }
    //尝试断开连接
    suspend fun tryDisconnect(username: String) {
        members[username]?.socket?.close()
        if(members.containsKey(username)) {
            members.remove(username)
        }
    }
}