package com.me.guanpj.mall.order.module.address.edit

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.ShipAddress
import com.me.guanpj.mall.order.module.address.edit.AddressEditContract
import com.me.guanpj.mall.order.service.ShipAddressService
import javax.inject.Inject

/*
  编辑收货人信息Presenter
 */
class AddressEditPresenter @Inject constructor() : BasePresenter<AddressEditContract.View>(), AddressEditContract.Presenter {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
      添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onAddShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      修改收货人信息
     */
    fun editShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onEditShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }
}
