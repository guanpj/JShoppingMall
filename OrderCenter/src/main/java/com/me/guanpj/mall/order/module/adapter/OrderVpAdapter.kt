package com.me.guanpj.mall.order.module.adapter

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.me.guanpj.mall.order.core.OrderConstant
import com.me.guanpj.mall.order.module.order.list.OrderListFragment

/*
  订单Tab对应ViewPager
 */
class OrderVpAdapter(fm: FragmentManager, context: Context)
    : FragmentPagerAdapter(fm) {

    private val titles = arrayOf("全部","待付款","待收货","已完成","已取消")

    override fun getItem(position: Int): Fragment {
        val fragment = OrderListFragment()
        val bundle = Bundle()
        bundle.putInt(OrderConstant.KEY_ORDER_STATUS,position)
        fragment.arguments = bundle
        return fragment
    }

    override fun getCount(): Int {
        return titles.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return titles[position]
    }
}
