package com.me.guanpj.mall.library.mvp.presenter

import android.content.Context
import com.me.guanpj.mall.library.mvp.IBasePresenter
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.util.NetWorkUtils
import com.trello.rxlifecycle.LifecycleProvider
import java.lang.ref.WeakReference
import java.lang.reflect.InvocationHandler
import java.lang.reflect.InvocationTargetException
import java.lang.reflect.Method
import javax.inject.Inject

open class BasePresenter<V : IBaseView> : IBasePresenter<V> {

    lateinit var proxyView: V
    private var weakView: WeakReference<V>? = null
    val isAttached: Boolean
        get() = this.weakView != null && this.weakView!!.get() != null

    @Inject
    lateinit var mLifecycleProvider: LifecycleProvider<*>

    @Inject
    lateinit var mContext: Context

    override fun onAttach(view: V) {
        this.proxyView = view
        /*this.weakView = WeakReference(view)
        this.getView() = Proxy.newProxyInstance(view.javaClass.classLoader,
                view.javaClass.interfaces, MyInvocationHandler(this.weakView!!.get())) as V*/
    }

    fun getView(): V {
        return proxyView!!
    }

    override fun onDetach() {
        if (this.weakView != null) {
            this.weakView!!.clear()
            this.weakView = null
        }
    }

    fun checkNetWork(): Boolean {
        if (NetWorkUtils.isNetWorkAvailable(mContext)) {
            return true
        }
        getView()?.onError("网络不可用")
        return false
    }

    private inner class MyInvocationHandler<T>(private val target: T) : InvocationHandler {
        @Throws(InvocationTargetException::class, IllegalAccessException::class)
        override fun invoke(o: Any, method: Method, objects: Array<Any>): Any? {
            return if (isAttached) {
                method.invoke(target, *objects)
            } else null
        }
    }
}