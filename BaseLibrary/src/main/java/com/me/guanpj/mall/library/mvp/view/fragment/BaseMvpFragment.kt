package com.me.guanpj.mall.library.mvp.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.library.core.BaseApplication
import com.me.guanpj.mall.library.di.component.DaggerFragmentComponent
import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.module.FragmentModule
import com.me.guanpj.mall.library.di.module.LifecycleProviderModule
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.widget.ProgressLoading
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), IBaseView {

    @Inject
    lateinit var mPresenter: P

    lateinit var mFragmentComponent: FragmentComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        initActivityInjection()
        performInject()

        mPresenter.onAttach(this)

        mLoadingDialog = ProgressLoading.create(context)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    fun initActivityInjection() {
        mFragmentComponent = DaggerFragmentComponent.builder().appComponent(BaseApplication.appComponent)
                .fragmentModule(FragmentModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    abstract fun performInject()

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text:String)= toast(text)
}