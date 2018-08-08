package com.kotlin.user.data.protocol

/*
    登录请求体
 */
data class LoginReq(val mobile:String, val pwd:String, val pushId:String)
