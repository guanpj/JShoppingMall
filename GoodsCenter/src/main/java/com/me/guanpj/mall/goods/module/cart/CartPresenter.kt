package com.me.guanpj.mall.goods.module.cart

import com.me.guanpj.mall.goods.data.CartGoods
import com.me.guanpj.mall.goods.service.CartService
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import javax.inject.Inject

/*
  购物车 Presenter
 */
class CartPresenter @Inject constructor() : BasePresenter<CartContract.View>(), CartContract.Presenter {

    @Inject
    lateinit var cartService: CartService


    /*
      获取购物车列表
     */
    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        cartService.getCartList().excute(object : BaseSubscriber<MutableList<CartGoods>?>(getView()) {
            override fun onNext(t: MutableList<CartGoods>?) {
                getView().onGetCartListResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      删除购物车商品
     */
    fun deleteCartList(list: List<Int>) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        cartService.deleteCartList(list).excute(object : BaseSubscriber<Boolean>(getView()) {
            override fun onNext(t: Boolean) {
                getView().onDeleteCartListResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      提交购物车商品
     */
    fun submitCart(list: MutableList<CartGoods>, totalPrice: Long) {
        if (!checkNetWork()) {
            return
        }
        getView().showLoading()
        cartService.submitCart(list, totalPrice).excute(object : BaseSubscriber<Int>(getView()) {
            override fun onNext(t: Int) {
                getView().onSubmitCartListResult(t)
            }
        }, mLifecycleProvider)
    }
}
