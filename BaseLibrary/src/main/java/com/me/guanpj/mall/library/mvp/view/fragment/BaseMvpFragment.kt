package com.me.guanpj.mall.library.mvp.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.widget.ProgressLoading
import dagger.android.support.AndroidSupportInjection
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

abstract class BaseMvpFragment<P : BasePresenter<*>> : BaseFragment(), IBaseView {

    @Inject
    lateinit var mPresenter: P

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        //初始加载框
        mLoadingDialog = ProgressLoading.create(context)
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text:String)= toast(text)
}