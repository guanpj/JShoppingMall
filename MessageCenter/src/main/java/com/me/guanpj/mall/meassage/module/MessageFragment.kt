package com.kotlin.message.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.eightbitlab.rxbus.Bus
import com.kennyc.view.MultiStateView
import com.kotlin.message.ui.adapter.MessageAdapter
import com.me.guanpj.mall.library.ext.startLoading
import com.me.guanpj.mall.library.mvp.view.fragment.BaseMvpFragment
import com.me.guanpj.mall.meassage.R
import com.me.guanpj.mall.meassage.data.Message
import com.me.guanpj.mall.meassage.module.MessageContract
import com.me.guanpj.mall.meassage.module.MessagePresenter
import com.me.guanpj.mall.provider.event.MessageBadgeEvent
import kotlinx.android.synthetic.main.fragment_message.*

/*
  消息列表Fragment
 */
class MessageFragment : BaseMvpFragment<MessagePresenter>(), MessageContract.View {

    private lateinit var mAdapter: MessageAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_message, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
    }

    /*
      初始化视图
     */
    private fun initView() {
        mMessageRv.layoutManager = LinearLayoutManager(context)
        mAdapter = MessageAdapter(context!!)
        mMessageRv.adapter = mAdapter
    }

    override fun onStart() {
        super.onStart()
        loadData()
    }

    /*
      加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getMessageList()
    }

    /*
      获取消息后回调
     */
    override fun onGetMessageResult(result: MutableList<Message>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            //数据为空
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    /*
      监听Fragment隐藏或显示
     */
    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!hidden) {
            Bus.send(MessageBadgeEvent(false))
        }
    }
}
