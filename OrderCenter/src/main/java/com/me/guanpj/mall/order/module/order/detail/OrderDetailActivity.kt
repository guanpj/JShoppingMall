package com.me.guanpj.mall.order.module.order.detail

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Route
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.library.util.YuanFenConverter
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.data.Order
import com.me.guanpj.mall.order.di.component.DaggerOrderActivityComponent
import com.me.guanpj.mall.order.di.module.OrderModule
import com.me.guanpj.mall.order.module.adapter.OrderGoodsAdapter
import com.me.guanpj.mall.provider.common.ProviderConstant
import com.me.guanpj.mall.provider.router.RouterPath
import kotlinx.android.synthetic.main.activity_order_detail.*

/*
  订单详情
 */
@Route(path = RouterPath.MessageCenter.PATH_MESSAGE_ORDER)
class OrderDetailActivity : BaseMvpActivity<OrderDetailPresenter>(), OrderDetailContract.View {
    private lateinit var mAdapter: OrderGoodsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
        loadData()
    }

    override fun performInject() {
        DaggerOrderActivityComponent.builder().activityComponent(mActivityComponent)
                .orderModule(OrderModule()).build().inject(this)
    }

    /*
      初始化视图
     */
    private fun initView() {
        mOrderGoodsRv.layoutManager = LinearLayoutManager(this)
        mAdapter = OrderGoodsAdapter(this)
        mOrderGoodsRv.adapter = mAdapter
    }

    /*
      加载数据
     */
    private fun loadData() {
        mPresenter.getOrderById(intent.getIntExtra(ProviderConstant.KEY_ORDER_ID, -1))
    }

    /*
      获取订单回调
     */
    override fun onGetOrderByIdResult(result: Order) {
        mShipNameTv.setContentText(result.shipAddress!!.shipUserName)
        mShipMobileTv.setContentText(result.shipAddress!!.shipUserMobile)
        mShipAddressTv.setContentText(result.shipAddress!!.shipAddress)
        mTotalPriceTv.setContentText(YuanFenConverter.changeF2YWithUnit(result.totalPrice))

        mAdapter.setData(result.orderGoodsList)
    }

}
