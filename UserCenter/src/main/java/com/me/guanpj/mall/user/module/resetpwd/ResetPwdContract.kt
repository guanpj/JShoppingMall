package com.me.guanpj.mall.user.module.resetpwd

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface ResetPwdContract {
    interface View : IBaseView {
        fun onResetPwdResult(result:String)
    }

    interface Presenter : IBasePresenter<View> {

    }
}