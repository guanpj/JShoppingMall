package com.kotlin.user.data.protocol

/*
  注册请求体
 */
data class RegisterReq(val mobile:String,val pwd:String,val verifyCode:String)
