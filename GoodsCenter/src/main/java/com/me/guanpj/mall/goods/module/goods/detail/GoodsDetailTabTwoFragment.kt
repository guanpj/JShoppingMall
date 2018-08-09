package com.me.guanpj.mall.goods.module.goods.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.event.GoodsDetailImageEvent
import com.me.guanpj.mall.library.ext.loadUrl
import com.me.guanpj.mall.library.mvp.view.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_two.*

/*
  商品详情Tab Two
 */
class GoodsDetailTabTwoFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_two, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObserve()
    }

    /*
      初始化监听，商品详情获取成功后，加载当前页面
     */
    private fun initObserve() {
        Bus.observe<GoodsDetailImageEvent>()
                .subscribe {
                    t: GoodsDetailImageEvent ->
                    run {
                        mGoodsDetailOneIv.loadUrl(t.imgOne)
                        mGoodsDetailTwoIv.loadUrl(t.imgTwo)
                    }
                }
                .registerInBus(this)

    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
