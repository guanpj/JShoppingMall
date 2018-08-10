package com.me.guanpj.mall.user.module.forgetpwd

import android.os.Bundle
import android.view.View
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.ext.enable
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.user.R
import com.me.guanpj.mall.user.module.resetpwd.ResetPwdActivity
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
  忘记密码界面
 */
class ForgetPwdActivity : BaseMvpActivity<ForgetPwdPresenter>(), ForgetPwdContract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

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
        mNextBtn.enable(mMobileEt, { isBtnEnable() })
        mNextBtn.enable(mVerifyCodeEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mNextBtn.onClick(this)
    }

    /*
      点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mVerifyCodeBtn -> {
                mVerifyCodeBtn.requestSendVerifyNumber()
                toast("发送验证成功")
            }

            R.id.mNextBtn -> {
                mPresenter.forgetPwd(mMobileEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    /*
      判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not()
    }

    /*
      忘记密码回调
     */
    override fun onForgetPwdResult(result: String) {
        toast(result)
        startActivity<ResetPwdActivity>("mobile" to mMobileEt.text.toString())
    }
}
