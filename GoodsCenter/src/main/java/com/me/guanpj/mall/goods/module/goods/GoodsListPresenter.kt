package com.me.guanpj.mall.goods.module.goods

import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.goods.service.GoodsService
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import javax.inject.Inject

/*
    商品列表 Presenter
 */
class GoodsListPresenter @Inject constructor() : BasePresenter<GoodListContract.View>(), GoodListContract.Presenter {

    @Inject
    lateinit var goodsService: GoodsService


    /*
      获取商品列表
     */
    fun getGoodsList(categoryId: Int, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsList(categoryId, pageNo).excute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        }, mLifecycleProvider)
    }

    /*
      根据关键字 搜索商品
     */
    fun getGoodsListByKeyword(keyword: String, pageNo: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        goodsService.getGoodsListByKeyword(keyword, pageNo).excute(object : BaseSubscriber<MutableList<Goods>?>(mView) {
            override fun onNext(t: MutableList<Goods>?) {
                mView.onGetGoodsListResult(t)
            }
        }, mLifecycleProvider)
    }

}
