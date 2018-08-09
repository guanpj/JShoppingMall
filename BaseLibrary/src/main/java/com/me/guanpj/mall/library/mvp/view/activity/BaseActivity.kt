package com.me.guanpj.mall.library.mvp.view.activity

import android.view.View
import android.widget.FrameLayout
import com.trello.rxlifecycle.components.support.RxAppCompatActivity
import org.jetbrains.anko.find

open class BaseActivity : RxAppCompatActivity() {
    //获取Window中视图content
    val contentView: View
        get() {
            val content = find<FrameLayout>(android.R.id.content)
            return content.getChildAt(0)
        }
}