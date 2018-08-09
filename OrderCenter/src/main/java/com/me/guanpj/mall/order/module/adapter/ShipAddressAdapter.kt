package com.me.guanpj.mall.order.module.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.adapter.BaseRecyclerViewAdapter
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.data.ShipAddress
import kotlinx.android.synthetic.main.layout_address_item.view.*

/*
  收货地址数据适配
 */
class ShipAddressAdapter(context: Context) : BaseRecyclerViewAdapter<ShipAddress, ShipAddressAdapter.ViewHolder>(context) {

    var mOptClickListener: OnOptClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_address_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]

        holder.itemView.mSetDefaultTv.isSelected = (model.shipIsDefault == 0)
        holder.itemView.mShipNameTv.text = model.shipUserName + "    " + model.shipUserMobile
        holder.itemView.mShipAddressTv.text = model.shipAddress

        holder.itemView.mSetDefaultTv.onClick {
            mOptClickListener?.let {
                if (holder.itemView.mSetDefaultTv.isSelected) {
                    return@onClick
                }
                model.shipIsDefault = 0
                it.onSetDefault(model)
            }
        }

        holder.itemView.mEditTv.onClick {
            mOptClickListener?.let {
                it.onEdit(model)
            }
        }

        holder.itemView.mDeleteTv.onClick {
            mOptClickListener?.let {
                it.onDelete(model)
            }
        }
    }


    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)

    /*
      对应操作接口
     */
    interface OnOptClickListener {
        fun onSetDefault(address: ShipAddress)
        fun onEdit(address: ShipAddress)
        fun onDelete(address: ShipAddress)
    }
}
