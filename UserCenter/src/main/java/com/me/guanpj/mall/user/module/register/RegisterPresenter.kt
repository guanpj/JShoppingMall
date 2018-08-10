package com.me.guanpj.mall.user.module.register

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.user.service.UserService
import javax.inject.Inject

/*
  用户注册Presenter
 */
class RegisterPresenter @Inject constructor() : BasePresenter<RegisterContract.View>(), RegisterContract.Presenter {

    @Inject
    lateinit var userService: UserService

    fun register(mobile: String, pwd: String, verifyCode: String) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()

        userService.register(mobile, pwd, verifyCode).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                if (t)
                    getView().onRegisterResult("注册成功")
            }
        }, mLifecycleProvider)
    }
}
