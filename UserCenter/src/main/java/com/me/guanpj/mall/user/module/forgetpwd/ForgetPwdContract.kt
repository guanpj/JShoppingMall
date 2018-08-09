package com.me.guanpj.mall.user.module.forgetpwd

import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface ForgetPwdContract {
    interface View : IBaseView {
        /*
          忘记密码回调
        */
        fun onForgetPwdResult(result: String)
    }

    interface Presenter : IBasePresenter<View> {

    }
}