package com.me.guanpj.mall.user.module.user

import com.kotlin.user.data.protocol.UserInfo
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView

interface UserInfoContract {
    interface View : IBaseView {
        /*
          获取上传凭证回调
         */
        fun onGetUploadTokenResult(result:String)

        /*
          编辑用户资料回调
         */
        fun onEditUserResult(result: UserInfo)
    }

    interface Presenter : IBasePresenter<View> {

    }
}