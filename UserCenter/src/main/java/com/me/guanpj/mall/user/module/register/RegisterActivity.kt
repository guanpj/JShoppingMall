package com.me.guanpj.mall.user.module.register

import android.os.Bundle
import android.view.View
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.ext.enable
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.user.R
import kotlinx.android.synthetic.main.activity_register.*
import org.jetbrains.anko.toast

/*
  注册界面
 */
class RegisterActivity : BaseMvpActivity<RegisterPresenter>(), RegisterContract.View, View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

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
        mRegisterBtn.enable(mMobileEt, { isBtnEnable() })
        mRegisterBtn.enable(mVerifyCodeEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdEt, { isBtnEnable() })
        mRegisterBtn.enable(mPwdConfirmEt, { isBtnEnable() })

        mVerifyCodeBtn.onClick(this)
        mRegisterBtn.onClick(this)
    }

    /*
      注册回调
     */
    override fun onRegisterResult(result: String) {
        toast(result)
        finish()
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

            R.id.mRegisterBtn -> {
                mPresenter.register(mMobileEt.text.toString(), mPwdEt.text.toString(), mVerifyCodeEt.text.toString())
            }
        }
    }

    /*
      判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mVerifyCodeEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not() &&
                mPwdConfirmEt.text.isNullOrEmpty().not()
    }
}
