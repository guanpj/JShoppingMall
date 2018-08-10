package com.me.guanpj.mall.user.module.forgetpwd

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.user.service.UserService
import javax.inject.Inject

/*
  忘记密码Presenter
 */
class ForgetPwdPresenter @Inject constructor() : BasePresenter<ForgetPwdContract.View>(), ForgetPwdContract.Presenter {

    @Inject
    lateinit var userService: UserService

    fun forgetPwd(mobile: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()

        userService.forgetPwd(mobile, verifyCode).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                if (t)
                    getView().onForgetPwdResult("验证成功")
            }
        }, mLifecycleProvider)

    }

}
