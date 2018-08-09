package com.me.guanpj.mall.order.module.address.list

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.ext.startLoading
import com.me.guanpj.mall.library.mvp.adapter.BaseRecyclerViewAdapter
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.order.R
import com.me.guanpj.mall.order.core.OrderConstant
import com.me.guanpj.mall.order.data.ShipAddress
import com.me.guanpj.mall.order.event.SelectAddressEvent
import com.me.guanpj.mall.order.module.adapter.ShipAddressAdapter
import com.me.guanpj.mall.order.module.address.edit.AddressEditActivity
import kotlinx.android.synthetic.main.activity_address.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
  收货人信息列表页
 */
class AddressListActivity : BaseMvpActivity<AddressListPresenter>(), AddressListContract.View {

    private lateinit var mAdapter: ShipAddressAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_address)

        initView()
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
      初始化视图
     */
    private fun initView() {
        mAddressRv.layoutManager = LinearLayoutManager(this)
        mAdapter = ShipAddressAdapter(this)
        mAddressRv.adapter = mAdapter

        //设置操作事件
        mAdapter.mOptClickListener = object : ShipAddressAdapter.OnOptClickListener {
            override fun onSetDefault(address: ShipAddress) {
                mPresenter.setDefaultShipAddress(address)
            }

            override fun onEdit(address: ShipAddress) {
                startActivity<AddressEditActivity>(OrderConstant.KEY_SHIP_ADDRESS to address)
            }

            override fun onDelete(address: ShipAddress) {
                AlertView("删除", "确定删除该地址？", "取消", null, arrayOf("确定"), this@AddressListActivity, AlertView.Style.Alert, OnItemClickListener { o, position ->
                    if (position == 0) {
                        mPresenter.deleteShipAddress(address.id)
                    }
                }).show()
            }
        }

        //单项点击事件
        mAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<ShipAddress> {
            override fun onItemClick(item: ShipAddress, position: Int) {
                Bus.send(SelectAddressEvent(item))
                finish()
            }
        })


        mAddAddressBtn.onClick {
            startActivity<AddressEditActivity>()
        }
    }

    /*
      加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getShipAddressList()
    }

    /*
      获取收货人信息回调
     */
    override fun onGetShipAddressResult(result: MutableList<ShipAddress>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
      设置默认收货人回调
     */
    override fun onSetDefaultResult(result: Boolean) {
        toast("设置默认成功")
        loadData()
    }

    /*
      删除收货人回调
     */
    override fun onDeleteResult(result: Boolean) {
        toast("删除成功")
        loadData()
    }
}
