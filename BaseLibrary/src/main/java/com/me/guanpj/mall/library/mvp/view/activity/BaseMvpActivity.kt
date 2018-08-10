package com.me.guanpj.mall.library.mvp.view.activity

import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.me.guanpj.mall.library.core.BaseApplication
import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.component.DaggerActivityComponent
import com.me.guanpj.mall.library.di.module.ActivityModule
import com.me.guanpj.mall.library.di.module.LifecycleProviderModule
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.widget.ProgressLoading
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), IBaseView {

    @Inject
    lateinit var mPresenter: P

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        performInject()

        mPresenter.proxyView = this

        mLoadingDialog = ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder().appComponent(BaseApplication.appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    abstract fun performInject()

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text: String) = toast(text)

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.onDetach()
    }
}