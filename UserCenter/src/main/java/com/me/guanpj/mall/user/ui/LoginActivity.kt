package com.me.guanpj.mall.user.ui

import android.os.Bundle
import com.kotlin.user.data.protocol.UserInfo
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import org.jetbrains.anko.toast

class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View {
    override fun onLoginResult(result: UserInfo) {
        toast(result.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.login("111", "222", "ddd")
    }
}