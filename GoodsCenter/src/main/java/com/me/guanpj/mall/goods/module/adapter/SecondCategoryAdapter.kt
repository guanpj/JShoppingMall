package com.me.guanpj.mall.goods.module.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.library.ext.loadUrl
import com.me.guanpj.mall.library.mvp.adapter.BaseRecyclerViewAdapter
import kotlinx.android.synthetic.main.layout_second_category_item.view.*

/*
  二级商品分类Adapter
 */
class SecondCategoryAdapter(context: Context): BaseRecyclerViewAdapter<Category, SecondCategoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_second_category_item,parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        //分类图片
        holder.itemView.mCategoryIconIv.loadUrl(model.categoryIcon)
        //分类名称
        holder.itemView.mSecondCategoryNameTv.text = model.categoryName
    }

    class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}
