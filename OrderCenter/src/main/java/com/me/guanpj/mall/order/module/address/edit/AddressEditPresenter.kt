package com.me.guanpj.mall.order.module.address.edit

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.Address
import com.me.guanpj.mall.order.service.AddressService
import javax.inject.Inject

/*
  编辑收货人信息Presenter
 */
class AddressEditPresenter @Inject constructor() : BasePresenter<AddressEditContract.View>(), AddressEditContract.Presenter {

    @Inject
    lateinit var shipAddressService: AddressService

    /*
      添加收货人信息
     */
    fun addShipAddress(shipUserName: String, shipUserMobile: String, shipAddress: String) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        shipAddressService.addShipAddress(shipUserName, shipUserMobile, shipAddress).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onAddShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      修改收货人信息
     */
    fun editShipAddress(address: Address) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onEditShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }
}
