package com.me.guanpj.mall.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.me.guanpj.mall.R
import com.me.guanpj.mall.library.ext.loadUrl
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.fragment.BaseFragment
import com.me.guanpj.mall.library.util.AppPrefsUtils
import com.me.guanpj.mall.order.core.OrderConstant
import com.me.guanpj.mall.order.core.OrderStatus
import com.me.guanpj.mall.order.module.address.list.AddressListActivity
import com.me.guanpj.mall.order.module.order.list.OrderActivity
import com.me.guanpj.mall.provider.common.ProviderConstant
import com.me.guanpj.mall.provider.common.afterLogin
import com.me.guanpj.mall.provider.common.isLogined
import com.me.guanpj.mall.ui.activity.SettingActivity
import com.me.guanpj.mall.user.module.user.UserInfoActivity
import kotlinx.android.synthetic.main.fragment_me.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

/*
  "我的"界面
 */
class MeFragment : BaseFragment(), View.OnClickListener {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_me, null)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /*
        初始化视图
     */
    private fun initView() {
        mUserIconIv.onClick(this)
        mUserNameTv.onClick(this)

        mWaitPayOrderTv.onClick(this)
        mWaitConfirmOrderTv.onClick(this)
        mCompleteOrderTv.onClick(this)
        mAllOrderTv.onClick(this)
        mAddressTv.onClick(this)
        mShareTv.onClick(this)
        mSettingTv.onClick(this)
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
      加载初始数据
     */
    private fun loadData() {
        if (isLogined()) {
            val userIcon = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_ICON)
            if (userIcon.isNotEmpty()) {
                mUserIconIv.loadUrl(userIcon)
            }
            mUserNameTv.text = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_NAME)
        } else {
            mUserIconIv.setImageResource(R.drawable.icon_default_user)
            mUserNameTv.text = getString(R.string.un_login_text)
        }
    }

    /*
        点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mUserIconIv, R.id.mUserNameTv -> {
                afterLogin {
                    startActivity<UserInfoActivity>()
                }
            }

            R.id.mWaitPayOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_PAY)
            }
            R.id.mWaitConfirmOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_WAIT_CONFIRM)
            }
            R.id.mCompleteOrderTv -> {
                startActivity<OrderActivity>(OrderConstant.KEY_ORDER_STATUS to OrderStatus.ORDER_COMPLETED)
            }
            R.id.mAllOrderTv -> {
                afterLogin {
                    startActivity<OrderActivity>()
                }
            }

            R.id.mAddressTv -> {
                afterLogin {
                    startActivity<AddressListActivity>()
                }
            }
            R.id.mShareTv -> {
                toast(R.string.coming_soon_tip)
            }
            R.id.mSettingTv -> {
                startActivity<SettingActivity>()
            }
        }
    }

}
