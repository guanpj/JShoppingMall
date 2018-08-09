package com.me.guanpj.mall.order.service

import com.me.guanpj.mall.order.data.ShipAddress
import rx.Observable

/*
  收货人信息业务接口
 */
interface ShipAddressService {

    /*
      添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean>

    /*
      获取收货地址列表
     */
    fun getShipAddressList(): Observable<MutableList<ShipAddress>?>

    /*
      修改收货地址
     */
    fun editShipAddress(address:ShipAddress): Observable<Boolean>

    /*
      删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<Boolean>
}
