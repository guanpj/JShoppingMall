package com.me.guanpj.mall.goods.module.goods.detail

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.view.Gravity
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.core.GoodsConstant
import com.me.guanpj.mall.goods.event.AddCartEvent
import com.me.guanpj.mall.goods.event.UpdateCartSizeEvent
import com.me.guanpj.mall.goods.module.adapter.GoodsDetailVpAdapter
import com.me.guanpj.mall.goods.module.cart.CartActivity
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseActivity
import com.me.guanpj.mall.library.util.AppPrefsUtils
import com.me.guanpj.mall.provider.common.afterLogin
import kotlinx.android.synthetic.main.activity_goods_detail.*
import org.jetbrains.anko.startActivity
import q.rorbin.badgeview.QBadgeView

/*
  商品详情 Activity
 */
class GoodsDetailActivity : BaseActivity() {

    private lateinit var mCartBdage: QBadgeView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_goods_detail)
        initView()
        initObserve()
        loadCartSize()
    }

    /*
      初始化视图
     */
    private fun initView() {
        mGoodsDetailTab.tabMode = TabLayout.MODE_FIXED
        mGoodsDetailVp.adapter = GoodsDetailVpAdapter(supportFragmentManager, this)
        //TabLayout关联ViewPager
        mGoodsDetailTab.setupWithViewPager(mGoodsDetailVp)

        mAddCartBtn.onClick {
            afterLogin {
                Bus.send(AddCartEvent())
            }
        }

        mEnterCartTv.onClick {
            startActivity<CartActivity>()
        }

        mLeftIv.onClick {
            finish()
        }

        mCartBdage = QBadgeView(this)
    }

    /*
      加载购物车数量
     */
    private fun loadCartSize() {
        setCartBadge()
    }

    /*
      监听购物车数量变化
     */
    private fun initObserve() {
        Bus.observe<UpdateCartSizeEvent>()
                .subscribe {
                    setCartBadge()
                }.registerInBus(this)
    }

    /*
      设置购物车标签
     */
    private fun setCartBadge() {
        mCartBdage.badgeGravity = Gravity.END or Gravity.TOP
        mCartBdage.setGravityOffset(22f, -2f, true)
        mCartBdage.setBadgeTextSize(6f, true)
        mCartBdage.bindTarget(mEnterCartTv).badgeNumber = AppPrefsUtils.instant.getInt(GoodsConstant.SP_CART_SIZE)

    }

    /*
      Bus取消监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }
}
