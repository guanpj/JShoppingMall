package com.me.guanpj.mall.order.service.impl

import com.me.guanpj.mall.library.ext.convert
import com.me.guanpj.mall.library.ext.convertBoolean
import com.me.guanpj.mall.order.data.Address
import com.me.guanpj.mall.order.data.repository.AddressRepository
import com.me.guanpj.mall.order.service.AddressService
import rx.Observable
import javax.inject.Inject

/*
  收货人信息 业务实现类
 */
class AddressServiceImpl @Inject constructor(): AddressService {

    @Inject
    lateinit var repository: AddressRepository

    /*
      添加收货人信息
     */
    override fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String): Observable<Boolean> {
        return repository.addShipAddress(shipUserName,shipUserMobile,shipAddress).convertBoolean()
    }

    /*
      获取收货人信息列表
     */
    override fun getShipAddressList(): Observable<MutableList<Address>?> {
        return repository.getShipAddressList().convert()
    }

    /*
      修改收货人信息
     */
    override fun editShipAddress(address: Address): Observable<Boolean> {
        return  repository.editShipAddress(address).convertBoolean()
    }

    /*
      删除收货人信息
     */
    override fun deleteShipAddress(id: Int): Observable<Boolean> {
        return repository.deleteShipAddress(id).convertBoolean()
    }
}
