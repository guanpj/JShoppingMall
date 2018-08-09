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
class CartListPresenter @Inject constructor() : BasePresenter<CartListContract.View>(), CartListContract.Presenter {

    @Inject
    lateinit var cartService: CartService


    /*
      获取购物车列表
     */
    fun getCartList() {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.getCartList().excute(object : BaseSubscriber<MutableList<CartGoods>?>(mView) {
            override fun onNext(t: MutableList<CartGoods>?) {
                mView.onGetCartListResult(t)
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
        mView.showLoading()
        cartService.deleteCartList(list).excute(object : BaseSubscriber<Boolean>(mView) {
            override fun onNext(t: Boolean) {
                mView.onDeleteCartListResult(t)
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
        mView.showLoading()
        cartService.submitCart(list, totalPrice).excute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                mView.onSubmitCartListResult(t)
            }
        }, mLifecycleProvider)
    }
}
