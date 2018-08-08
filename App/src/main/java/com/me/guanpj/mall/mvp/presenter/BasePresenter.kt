package com.me.guanpj.mall.mvp.presenter

import android.content.Context
import com.me.guanpj.mall.core.MyApplication.Companion.context
import com.me.guanpj.mall.library.util.NetWorkUtils
import com.me.guanpj.mall.mvp.BaseView
import com.trello.rxlifecycle.LifecycleProvider
import javax.inject.Inject

open class BasePresenter<V : BaseView> {

    lateinit var mView: V

    @Inject
    lateinit var mLifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var mContext: Context

    fun checkNetWork():Boolean{
        if(NetWorkUtils.isNetWorkAvailable(context)){
            return true
        }
        mView.onError("网络不可用")
        return false
    }
}