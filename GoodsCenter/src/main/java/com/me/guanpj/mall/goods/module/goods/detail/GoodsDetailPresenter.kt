package com.me.guanpj.mall.goods.module.goods.detail

import com.me.guanpj.mall.goods.core.GoodsConstant
import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.goods.service.CartService
import com.me.guanpj.mall.goods.service.GoodsService
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import com.me.guanpj.mall.library.util.AppPrefsUtils
import javax.inject.Inject

/*
  商品详情 Presenter
 */
class GoodsDetailPresenter @Inject constructor() : BasePresenter<GoodsDetailContract.View>(), GoodsDetailContract.Presenter {

    @Inject
    lateinit var goodsService: GoodsService

    @Inject
    lateinit var cartService: CartService

    /*
      获取商品详情
     */
    fun getGoodsDetailList(goodsId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsDetail(goodsId).excute(object : BaseSubscriber<Goods>(mView) {
            override fun onNext(t: Goods) {
                mView.onGetGoodsDetailResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      加入购物车
     */
    fun addCart(goodsId: Int, goodsDesc: String, goodsIcon: String, goodsPrice: Long,
                goodsCount: Int, goodsSku: String) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        cartService.addCart(goodsId, goodsDesc, goodsIcon, goodsPrice,
                goodsCount, goodsSku).excute(object : BaseSubscriber<Int>(mView) {
            override fun onNext(t: Int) {
                AppPrefsUtils.instant.putInt(GoodsConstant.SP_CART_SIZE, t)
                mView.onAddCartResult(t)
            }
        }, mLifecycleProvider)
    }
}
