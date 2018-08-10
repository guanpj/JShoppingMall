package com.me.guanpj.mall.order.data.repository

import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import com.me.guanpj.mall.order.data.AddAddressReq
import com.me.guanpj.mall.order.data.DeleteAddressReq
import com.me.guanpj.mall.order.data.EditAddressReq
import com.me.guanpj.mall.order.data.Address
import com.me.guanpj.mall.order.data.api.AddressApi
import rx.Observable
import javax.inject.Inject


/*
  收货地址数据层
 */
class AddressRepository @Inject constructor() {

    /*
      添加收货地址
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(AddressApi::class.java).addShipAddress(AddAddressReq(shipUserName,shipUserMobile,shipAddress))
    }

    /*
      删除收货地址
     */
    fun deleteShipAddress(id: Int): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(AddressApi::class.java).deleteShipAddress(DeleteAddressReq(id))
    }

    /*
      修改收货地址
     */
    fun editShipAddress(address: Address): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(AddressApi::class.java).editShipAddress(EditAddressReq(address.id,address.shipUserName,address.shipUserMobile,address.shipAddress,address.shipIsDefault))
    }

    /*
      获取收货地址列表
     */
    fun getShipAddressList(): Observable<BaseResp<MutableList<Address>?>> {
        return RetrofitFactory.instance.create(AddressApi::class.java).getShipAddressList()
    }
}
