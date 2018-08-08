package com.me.guanpj.mall.library.mvp

interface IBaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
}