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

        mView.showLoading()
        uploadService.getUploadToken().excute(object : BaseSubscriber<String>(mView) {
            override fun onNext(t: String) {
                mView.onGetUploadTokenResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      编辑用户资料
     */
    fun editUser(userIcon: String, userName: String, userGender: String, userSign: String) {
        if (!checkNetWork())
            return

        mView.showLoading()
        userService.editUser(userIcon, userName, userGender, userSign).excute(object : BaseSubscriber<UserInfo>(mView) {
            override fun onNext(t: UserInfo) {
                mView.onEditUserResult(t)
            }
        }, mLifecycleProvider)
    }
}
