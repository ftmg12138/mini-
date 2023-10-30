package com.zzh.chatroom0.presentation.username

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.zzh.chatroom0.R
import kotlinx.coroutines.flow.collectLatest


@Composable
fun UsernameScreen(
    viewModel: UsernameViewModel = hiltViewModel(),
    onNavigate: (String) -> Unit//这里我们使用了一个onNavigate函数来处理页面跳转
) {
    //这里我们使用了LaunchedEffect来监听onJoinChat事件，当onJoinChat事件发生时，我们就会跳转到聊天页面
    LaunchedEffect(key1 = true) {
        viewModel.onJoinChat.collectLatest { username ->
            onNavigate("chat_screen/$username")
        }
    }
    //这里我们使用了Box来布局，Box是一个容器，它可以将子组件放置在它的中心位置
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(R.drawable.background2),
            contentDescription = "Background Image",
            contentScale = ContentScale.FillBounds, // 根据需要选择适当的内容缩放模式
            modifier = Modifier.fillMaxSize()
        )
        Spacer(modifier = Modifier.height(8.dp))//这里我们使用了Spacer来添加一个8dp的空间
        //这里我们使用了Column来布局，Column是一个垂直布局的容器，它可以将子组件垂直排列
        Column(
            modifier = Modifier.fillMaxSize(),//这里我们使用了fillMaxSize来让Column占满整个屏幕
            verticalArrangement = Arrangement.Top,//这里我们使用了Arrangement.Center来让子组件在垂直方向上居中排列
            horizontalAlignment = Alignment.End //这里我们使用了Alignment.End来让子组件在水平方向上靠右排列
        ) {
            Spacer(modifier = Modifier.height(148.dp))//这里我们使用了Spacer来添加一个8dp的空间
            //这里我们使用了TextField来接收用户输入的用户名
            val customFontFamily = Font(R.font.www)
            Text(
                text = "欢迎来到聊天室",
                color = Color.Black,
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                fontFamily = FontFamily(customFontFamily),
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.height(148.dp))//这里我们使用了Spacer来添加一个8dp的空间
            TextField(
                value = viewModel.usernameText.value,
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.DarkGray,
                ),
                textStyle = TextStyle(color = Color.White,
                    fontFamily = FontFamily(customFontFamily)),
                onValueChange = viewModel::onUsernameChange,//这里我们使用了onValueChange来监听用户输入的变化
                placeholder = {
                    Text(
                        text = "输入用户名",
                        color = Color.White,
                        fontStyle = FontStyle.Italic,
                        fontFamily = FontFamily(customFontFamily),
                        )
                },
                modifier = Modifier.fillMaxWidth()//这里我们使用了fillMaxWidth来让TextField占满整个屏幕
            )
            Spacer(modifier = Modifier.height(8.dp))//这里我们使用了Spacer来添加一个8dp的空间
            //这里我们使用了Button来处理用户点击事件
            Button(
                onClick = {
                    viewModel.onJoinClick()
                    viewModel.ClearText()
                }
                , colors = ButtonDefaults.buttonColors(
                    backgroundColor = Color.Green)
                ,
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(text = "加入聊天",
                    fontFamily = FontFamily(customFontFamily))
            }

        }
    }
}