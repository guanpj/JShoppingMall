package com.me.guanpj.mall.order.module.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.library.ext.loadUrl
import com.me.guanpj.mall.library.mvp.adapter.BaseRecyclerViewAdapter
import com.me.guanpj.mall.library.util.YuanFenConverter
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.data.OrderGoods
import kotlinx.android.synthetic.main.layout_order_goods_item.view.*

/*
  订单中商品列表
 */
class OrderGoodsAdapter(context: Context) : BaseRecyclerViewAdapter<OrderGoods, OrderGoodsAdapter.ViewHolder>(context) {

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_order_goods_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mGoodsIconIv.loadUrl(model.goodsIcon)
        holder.itemView.mGoodsDescTv.text = model.goodsDesc
        holder.itemView.mGoodsSkuTv.text = model.goodsSku
        holder.itemView.mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(model.goodsPrice)
        holder.itemView.mGoodsCountTv.text = "x${model.goodsCount}"

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

}
