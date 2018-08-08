package com.me.guanpj.mall.mvp

interface BaseView {
    fun showLoading()
    fun hideLoading()
    fun onError(text: String)
}