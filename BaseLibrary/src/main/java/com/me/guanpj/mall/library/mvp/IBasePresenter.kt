package com.me.guanpj.mall.library.mvp

interface IBasePresenter<V : IBaseView> {
    fun onAttach(view: V)

    fun onDetach()
}