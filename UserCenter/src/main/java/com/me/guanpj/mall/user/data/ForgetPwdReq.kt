package com.kotlin.user.data.protocol

/*
  忘记密码请求体
 */
data class ForgetPwdReq(val mobile:String, val verifyCode:String)
