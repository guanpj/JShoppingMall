package com.me.guanpj.mall.goods.module.goods.detail

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kotlin.goods.injection.component.DaggerGoodsFragmentComponent
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.core.GoodsConstant
import com.me.guanpj.mall.goods.data.Goods
import com.me.guanpj.mall.goods.di.module.GoodsModule
import com.me.guanpj.mall.goods.event.AddCartEvent
import com.me.guanpj.mall.goods.event.GoodsDetailImageEvent
import com.me.guanpj.mall.goods.event.SkuChangedEvent
import com.me.guanpj.mall.goods.event.UpdateCartSizeEvent
import com.me.guanpj.mall.goods.widget.GoodsSkuPopView
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseActivity
import com.me.guanpj.mall.library.mvp.view.fragment.BaseMvpFragment
import com.me.guanpj.mall.library.util.YuanFenConverter
import com.me.guanpj.mall.library.widget.BannerImageLoader
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*

/*
  商品详情Tab One
 */
class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailContract.View {
    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: Animation
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        mPresenter.onAttach(this)
        return inflater?.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }

    override fun performInject() {
        DaggerGoodsFragmentComponent.builder().fragmentComponent(mFragmentComponent)
                .goodsModule(GoodsModule()).build().inject(this)
    }

    /*
      初始化视图
     */
    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Accordion)
        mGoodsDetailBanner.setDelayTime(2000)
        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0
            )
            (activity as BaseActivity).contentView.startAnimation(mAnimationStart)
        }
    }

    /*
      初始化缩放动画
   */
    private fun initAnim() {
        mAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 500
        mAnimationStart.fillAfter = true

        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 500
        mAnimationEnd.fillAfter = true
    }

    /*
      初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity!!)
        mSkuPop.setOnDismissListener{
            (activity as BaseActivity).contentView.startAnimation(mAnimationEnd)
        }
    }

    /*
      加载数据
     */
    private fun loadData() {
        mPresenter.getGoodsDetailList(activity!!.intent.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /*
       获取商品详情 回调
     */
    override fun onGetGoodsDetailResult(result: Goods) {

        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)
    }

    /*
      加载SKU数据
     */
    private fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)

    }

    /*
      监听SKU变化及加入购物车事件
     */
    private fun initObserve(){
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() +GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount()+"件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
      取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
      加入购物车
     */
    private fun addCart(){
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
                    )
        }

    }

    /*
      加入购物车 回调
     */
    override fun onAddCartResult(result: Int) = Bus.send(UpdateCartSizeEvent())
}
