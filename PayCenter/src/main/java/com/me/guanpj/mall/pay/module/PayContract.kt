package com.me.guanpj.mall.pay.module

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface PayContract {
    interface View : IBaseView {
        //获取支付签名
        fun onGetSignResult(result: String)
        //同步支付成功状态
        fun onPayOrderResult(result: Boolean)
    }

    interface Presenter : IBasePresenter<View>
}