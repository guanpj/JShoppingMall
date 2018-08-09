package com.me.guanpj.mall.user.module.login

import com.kotlin.user.data.protocol.UserInfo
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.user.service.UserService
import javax.inject.Inject

/*
  登录界面 Presenter
 */
class LoginPresenter @Inject constructor() : BasePresenter<LoginContract.View>(), LoginContract.Presenter {

    @Inject
    lateinit var userService: UserService

    /*
      登录
     */
    fun login(mobile: String, pwd: String, pushId: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        userService.login(mobile, pwd, pushId).excute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                    mView.onLoginResult(t)
            }
        }, mLifecycleProvider)
    }
}
