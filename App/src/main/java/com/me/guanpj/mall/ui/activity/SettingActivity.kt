package com.me.guanpj.mall.ui.activity

import android.os.Bundle
import com.me.guanpj.mall.R
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseActivity
import com.me.guanpj.mall.user.util.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_setting.*
import org.jetbrains.anko.toast

/*
  设置界面
 */
class SettingActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        mUserProtocolTv.onClick {
            toast("用户协议")
        }
        mFeedBackTv.onClick {
            toast("反馈意见")
        }
        mAboutTv.onClick {
            toast("关于")
        }

        //退出登录，清空本地用户数据
        mLogoutBtn.onClick {
            UserPrefsUtils.putUserInfo(null)
            finish()
        }
    }
}
