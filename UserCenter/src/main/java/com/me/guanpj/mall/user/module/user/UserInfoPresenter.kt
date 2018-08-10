package com.me.guanpj.mall.user.module.user

import com.kotlin.user.data.protocol.UserInfo
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.user.service.UploadService
import com.me.guanpj.mall.user.service.UserService
import javax.inject.Inject

/*
  编辑用户资料Presenter
 */
class UserInfoPresenter @Inject constructor() : BasePresenter<UserInfoContract.View>(), UserInfoContract.Presenter {

    @Inject
    lateinit var userService: UserService

    @Inject
    lateinit var uploadService: UploadService

    /*
        获取七牛云上传凭证
     */
    fun getUploadToken() {
        if (!checkNetWork())
            return

        getView().showLoading()
        uploadService.getUploadToken().excute(object : BaseSubscriber<String>(getView()) {
            override fun onNext(t: String) {
                getView().onGetUploadTokenResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      编辑用户资料
     */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork())
            return

        getView().showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).excute(object : BaseSubscriber<UserInfo>(getView()) {
            override fun onNext(t: UserInfo) {
                getView().onEditUserResult(t)
            }
        }, mLifecycleProvider)
    }
}
