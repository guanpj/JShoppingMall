package com.me.guanpj.mall.order.module.address.edit

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface AddressEditContract {
    interface View : IBaseView {
        //添加收货人回调
        fun onAddShipAddressResult(result: Boolean)
        //修改收货人回调
        fun onEditShipAddressResult(result: Boolean)
    }

    interface Presenter : IBasePresenter<View>
}