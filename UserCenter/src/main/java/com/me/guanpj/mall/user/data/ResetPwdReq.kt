package com.kotlin.user.data.protocol

/*
  重置密码请求体
 */
data class ResetPwdReq(val mobile:String, val pwd:String)
