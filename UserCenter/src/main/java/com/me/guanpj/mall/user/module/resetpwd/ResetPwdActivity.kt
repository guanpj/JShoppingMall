package com.me.guanpj.mall.user.module.resetpwd

import android.os.Bundle
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.ext.enable
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.user.R
import com.me.guanpj.mall.user.module.login.LoginActivity
import kotlinx.android.synthetic.main.activity_reset_pwd.*
import org.jetbrains.anko.clearTop
import org.jetbrains.anko.intentFor
import org.jetbrains.anko.singleTop
import org.jetbrains.anko.toast

/*
  重置密码界面
 */
class ResetPwdActivity : BaseMvpActivity<ResetPwdPresenter>(), ResetPwdContract.View {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reset_pwd)

        initView()
    }

    override fun performInject() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
    }

    /*
      初始化视图
     */
    private fun initView() {
        mConfirmBtn.enable(mPwdEt, { isBtnEnable() })
        mConfirmBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mConfirmBtn.onClick {
            if (mPwdEt.text.toString() != mPwdConfirmEt.text.toString()) {
                toast("密码不一致")
                return@onClick
            }
            mPresenter.resetPwd(intent.getStringExtra("mobile"), mPwdEt.text.toString())
        }
    }

    /*
      判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        return mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }

    /*
      重置密码回调
     */
    override fun onResetPwdResult(result: String) {
        toast(result)
        startActivity(intentFor<LoginActivity>().singleTop().clearTop())
    }
}
