package com.me.guanpj.mall.goods.module.category

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.kennyc.view.MultiStateView
import com.kotlin.goods.injection.component.DaggerCategoryComponent
import com.me.guanpj.mall.goods.R
import com.me.guanpj.mall.goods.core.GoodsConstant
import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.goods.di.module.CategoryModule
import com.me.guanpj.mall.goods.module.adapter.SecondCategoryAdapter
import com.me.guanpj.mall.goods.module.adapter.TopCategoryAdapter
import com.me.guanpj.mall.goods.module.goods.list.GoodsListActivity
import com.me.guanpj.mall.library.ext.setVisible
import com.me.guanpj.mall.library.ext.startLoading
import com.me.guanpj.mall.library.mvp.adapter.BaseRecyclerViewAdapter
import com.me.guanpj.mall.library.mvp.view.fragment.BaseMvpFragment
import kotlinx.android.synthetic.main.fragment_category.*
import org.jetbrains.anko.support.v4.startActivity

/*
    商品分类 Fragment
 */
class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryContract.View {

    //一级分类Adapter
    lateinit var topAdapter: TopCategoryAdapter
    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater?.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    override fun performInject() {
        DaggerCategoryComponent.builder().fragmentComponent(mFragmentComponent)
                .categoryModule(CategoryModule()).build().inject(this)
    }

    /*
      初始化视图
     */
    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context!!)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context!!)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsListActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }
        })
    }

    /*
      加载数据
     */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }
        mPresenter.getCategory(parentId)
    }

    /*
      获取商品分类 回调
     */
    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }
}
