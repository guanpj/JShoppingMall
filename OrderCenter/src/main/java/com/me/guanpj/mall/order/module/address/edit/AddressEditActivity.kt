package com.me.guanpj.mall.order.module.address.edit

import android.os.Bundle
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.core.OrderConstant
import com.me.guanpj.mall.order.data.ShipAddress
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/*
  收货人编辑页面
 */
class AddressEditActivity : BaseMvpActivity<AddressEditPresenter>(), AddressEditContract.View {

    private var mAddress: ShipAddress? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    /*
      初始化视图
     */
    private fun initView() {
        mSaveBtn.onClick {
            if (mShipNameEt.text.isNullOrEmpty()) {
                toast("名称不能为空")
                return@onClick
            }
            if (mShipMobileEt.text.isNullOrEmpty()) {
                toast("电话不能为空")
                return@onClick
            }
            if (mShipAddressEt.text.isNullOrEmpty()) {
                toast("地址不能为空")
                return@onClick
            }
            if (mAddress == null) {
                mPresenter.addShipAddress(mShipNameEt.text.toString(),
                        mShipMobileEt.text.toString(),
                        mShipAddressEt.text.toString())
            } else {
                mAddress!!.shipUserName = mShipNameEt.text.toString()
                mAddress!!.shipUserMobile = mShipMobileEt.text.toString()
                mAddress!!.shipAddress = mShipAddressEt.text.toString()
                mPresenter.editShipAddress(mAddress!!)
            }
        }
    }

    /*
      初始化数据
     */
    private fun initData() {
        mAddress = intent.getParcelableExtra(OrderConstant.KEY_SHIP_ADDRESS)
        mAddress?.let {
            mShipNameEt.setText(it.shipUserName)
            mShipMobileEt.setText(it.shipUserMobile)
            mShipAddressEt.setText(it.shipAddress)
        }
    }

    /*
      添加收货人信息回调
     */
    override fun onAddShipAddressResult(result: Boolean) {
        toast("添加地址成功")
        finish()
    }

    /*
      修改收货人信息回调
     */
    override fun onEditShipAddressResult(result: Boolean) {
        toast("修改地址成功")
        finish()
    }
}
