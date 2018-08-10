package com.me.guanpj.mall.order.module.address.edit

import android.os.Bundle
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.core.OrderConstant
import com.me.guanpj.mall.order.data.Address
import com.me.guanpj.mall.order.di.component.DaggerAddressComponent
import com.me.guanpj.mall.order.di.module.AddressModule
import kotlinx.android.synthetic.main.activity_edit_address.*
import org.jetbrains.anko.toast

/*
  收货人编辑页面
 */
class AddressEditActivity : BaseMvpActivity<AddressEditPresenter>(), AddressEditContract.View {

    private var mAddress: Address? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onAttach(this)
        setContentView(R.layout.activity_edit_address)

        initView()
        initData()
    }

    override fun performInject() {
        DaggerAddressComponent.builder().activityComponent(mActivityComponent)
                .addressModule(AddressModule()).build().inject(this)
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
