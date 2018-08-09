package com.me.guanpj.mall.library.mvp.presenter

import android.content.Context
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.util.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<V : IBaseView> {

    lateinit var mView: V

    @Inject
    lateinit var mLifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var mContext: Context

    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(mContext)) {
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}