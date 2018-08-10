package com.me.guanpj.mall.order.data

/*
  修改收货地址
 */
data class EditAddressReq(val id:Int, val shipUserName:String, val shipUserMobile:String, val shipAddress:String, val shipIsDefault:Int)
