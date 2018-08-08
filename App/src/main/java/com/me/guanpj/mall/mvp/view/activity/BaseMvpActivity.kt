package com.me.guanpj.mall.mvp.view.activity

import android.os.Bundle
import android.support.v4.app.Fragment
import com.me.guanpj.mall.library.widget.ProgressLoading
import com.me.guanpj.mall.mvp.BaseView
import com.me.guanpj.mall.mvp.presenter.BasePresenter
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import org.jetbrains.anko.toast
import javax.inject.Inject

abstract class BaseMvpActivity<P : BasePresenter<*>> : BaseActivity(), BaseView, HasSupportFragmentInjector{

    lateinit var mPresenter: P

    @Inject
    lateinit var mFragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var mLoadingDialog:ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        mLoadingDialog = ProgressLoading.create(this)
        //ARouter.getInstance().inject(this)
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return mFragmentDispatchingAndroidInjector
    }

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text:String)= toast(text)
}