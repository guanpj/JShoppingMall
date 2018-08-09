package com.me.guanpj.mall.goods.module.category

import com.me.guanpj.mall.goods.data.Category
import com.me.guanpj.mall.goods.service.CategoryService
import com.me.guanpj.mall.library.ext.excute
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.rx.BaseSubscriber
import javax.inject.Inject

/*
  商品分类 Presenter
 */
class CategoryPresenter @Inject constructor() : BasePresenter<CategoryContract.View>(), CategoryContract.Presenter {

    @Inject
    lateinit var categoryService: CategoryService

    /*
      获取商品分类列表
     */
    fun getCategory(parentId: Int) {
        if (!checkNetWork()) {
            return
        }
        mView.showLoading()
        categoryService.getCategory(parentId).excute(object : BaseSubscriber<MutableList<Category>?>(mView) {
            override fun onNext(t: MutableList<Category>?) {
                mView.onGetCategoryResult(t)
            }
        }, mLifecycleProvider)
    }
}
