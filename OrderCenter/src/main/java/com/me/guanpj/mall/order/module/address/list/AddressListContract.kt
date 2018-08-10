package com.me.guanpj.mall.order.module.address.list

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.order.data.Address

interface AddressListContract {
    interface View : IBaseView {
        //获取收货人列表回调
        fun onGetShipAddressResult(result: MutableList<Address>?)
        //设置默认收货人回调
        fun onSetDefaultResult(result: Boolean)
        //删除收货人回调
        fun onDeleteResult(result: Boolean)
    }

    interface Presenter : IBasePresenter<View>
}