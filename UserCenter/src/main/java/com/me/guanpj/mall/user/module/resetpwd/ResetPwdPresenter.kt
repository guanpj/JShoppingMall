package com.me.guanpj.mall.user.module.resetpwd

import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.user.service.UserService
import javax.inject.Inject

/*
  重置密码Presenter
 */
class ResetPwdPresenter @Inject constructor() : BasePresenter<ResetPwdContract.View>(), ResetPwdContract.Presenter {

    @Inject
    lateinit var userService: UserService

    /*
      重置密码
     */
    fun resetPwd(mobile: String, pwd: String) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()

        userService.resetPwd(mobile, pwd).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                if (t)
                    getView().onResetPwdResult("重置密码成功")
            }
        }, mLifecycleProvider)

    }

}
