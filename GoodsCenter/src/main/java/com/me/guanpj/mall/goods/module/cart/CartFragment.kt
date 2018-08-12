package com.me.guanpj.mall.goods.module.cart

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.core.GoodsConstant
import com.me.guanpj.mall.goods.data.CartGoods
import com.me.guanpj.mall.goods.di.component.DaggerCartComponent
import com.me.guanpj.mall.goods.di.module.CartModule
import com.me.guanpj.mall.goods.event.CartAllCheckedEvent
import com.me.guanpj.mall.goods.event.UpdateCartSizeEvent
import com.me.guanpj.mall.goods.event.UpdateTotalPriceEvent
import com.me.guanpj.mall.goods.module.adapter.CartGoodsAdapter
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.ext.setVisible
import com.me.guanpj.mall.library.ext.startLoading
import com.me.guanpj.mall.library.mvp.view.fragment.BaseMvpFragment
import com.me.guanpj.mall.library.util.AppPrefsUtils
import com.me.guanpj.mall.library.util.YuanFenConverter
import com.me.guanpj.mall.provider.common.ProviderConstant
import com.me.guanpj.mall.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/*
  购物车 Fragment
 */
class CartFragment : BaseMvpFragment<CartPresenter>(), CartContract.View {

    private lateinit var mAdapter: CartGoodsAdapter

    private var mTotalPrice: Long = 0
    private var mIsEditMode: Boolean = false

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mPresenter.onAttach(this)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
        loadData()
    }

    override fun performInject() {
        DaggerCartComponent.builder().fragmentComponent(mFragmentComponent)
                .cartModule(CartModule()).build().inject(this)
    }

    /*
      初始化视图和事件
     */
    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context!!)
        mCartGoodsRv.adapter = mAdapter

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        //全选按钮事件
        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        //删除按钮事件
        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) { it.id }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList) { it }
            if (cartGoodsList.size == 0) {
                toast("请选择需要提交的数据")
            } else {
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }
        }
    }

    /*
      刷新是否为编辑状态
     */
    private fun refreshEditStatus() {
        mIsEditMode = getString(R.string.common_edit) == mHeaderBar.getRightText()

        mTotalPriceTv.setVisible(mIsEditMode.not())
        mSettleAccountsBtn.setVisible(mIsEditMode.not())
        mDeleteBtn.setVisible(mIsEditMode)

        if (mIsEditMode) {
            mHeaderBar.getRightView().text = getString(R.string.common_complete)
        } else {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.mapTo(cartGoodsList) { it }
            if (cartGoodsList.size > 0) {
                mPresenter.updateCartGoods(cartGoodsList)
            }
            mHeaderBar.getRightView().text = getString(R.string.common_edit)
        }
    }

    /*
      加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    /*
      获取购物车列表回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

        //本地存储并发送事件刷新UI
        AppPrefsUtils.instant.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()
    }

    override fun onUpdateCartGoodsResult(result: Int) {
        toast("更新购物车成功！")
    }

    /*
      注册监听
     */
    private fun initObserve() {
        Bus.observe<CartAllCheckedEvent>().subscribe { event: CartAllCheckedEvent ->
            run {
                mAllCheckedCb.isChecked = event.isAllChecked
                updateTotalPrice()
            }
        }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>().subscribe { event: UpdateTotalPriceEvent ->
            if (!mIsEditMode) {
                mPresenter.updateCartGoods(arrayListOf(event.good))
            }
            updateTotalPrice()
        }.registerInBus(this)
    }

    /*
      取消监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
      更新总价
     */
    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()

        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    /*
      删除购物车回调
     */
    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()
    }

    /*
      提交购物车回调
     */
    override fun onSubmitCartListResult(result: Int) {
        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
                .withInt(ProviderConstant.KEY_ORDER_ID, result)
                .navigation()
    }

    /*
      设置Back是否可见
     */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }
}
