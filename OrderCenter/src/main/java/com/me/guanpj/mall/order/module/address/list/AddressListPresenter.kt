package com.me.guanpj.mall.order.module.address.list

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.ShipAddress
import com.me.guanpj.mall.order.module.address.list.AddressListContract
import com.me.guanpj.mall.order.service.ShipAddressService
import javax.inject.Inject

/*
  收货人列表Presenter
 */
class AddressListPresenter @Inject constructor() : BasePresenter<AddressListContract.View>(), AddressListContract.Presenter {

    @Inject
    lateinit var shipAddressService: ShipAddressService

    /*
      获取收货人列表
     */
    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.getShipAddressList().excute(object : BaseSubscriber<MutableList<ShipAddress>?>(mView) {
            override fun onNext(t: MutableList<ShipAddress>?) {
                mView.onGetShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      设置默认收货人信息
     */
    fun setDefaultShipAddress(address: ShipAddress) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onSetDefaultResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      删除收货人信息
     */
    fun deleteShipAddress(id: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        shipAddressService.deleteShipAddress(id).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteResult(t)
            }
        }, mLifecycleProvider)
    }
}
