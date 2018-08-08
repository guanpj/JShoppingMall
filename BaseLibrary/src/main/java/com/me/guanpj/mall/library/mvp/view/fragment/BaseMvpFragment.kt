package com.me.guanpj.mall.library.mvp.view.fragment

import android.content.Context
import com.me.guanpj.mall.library.widget.ProgressLoading
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.support.v4.toast

abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), IBaseView {

    lateinit var mPresenter: P

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text:String)= toast(text)
}