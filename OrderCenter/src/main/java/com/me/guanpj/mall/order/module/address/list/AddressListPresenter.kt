package com.me.guanpj.mall.order.module.address.list

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.order.data.Address
import com.me.guanpj.mall.order.service.AddressService
import javax.inject.Inject

/*
  收货人列表Presenter
 */
class AddressListPresenter @Inject constructor() : BasePresenter<AddressListContract.View>(), AddressListContract.Presenter {

    @Inject
    lateinit var shipAddressService: AddressService

    /*
      获取收货人列表
     */
    fun getShipAddressList() {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        shipAddressService.getShipAddressList().excute(object : BaseSubscriber<MutableList<Address>?>(getView()) {
            override fun onNext(t: MutableList<Address>?) {
                getView().onGetShipAddressResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      设置默认收货人信息
     */
    fun setDefaultShipAddress(address: Address) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        shipAddressService.editShipAddress(address).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onSetDefaultResult(t)
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
        getView().showLoading()
        shipAddressService.deleteShipAddress(id).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onDeleteResult(t)
            }
        }, mLifecycleProvider)
    }
}
