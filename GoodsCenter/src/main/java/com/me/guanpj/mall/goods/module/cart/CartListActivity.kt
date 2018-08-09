package com.me.guanpj.mall.goods.module.cart

import android.os.Bundle
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.library.mvp.view.activity.BaseActivity

/*
  购物车Activity 只是Fragment一个壳
 */
class CartListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)

        val fragment = supportFragmentManager.findFragmentById(R.id.fragment_cart)
        (fragment as CartListFragment).setBackVisible(true)
    }
}
