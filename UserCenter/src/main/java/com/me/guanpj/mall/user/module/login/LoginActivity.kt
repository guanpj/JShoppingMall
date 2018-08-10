package com.me.guanpj.mall.user.module.login

import android.os.Bundle
import android.view.View
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.ext.enable
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseMvpActivity
import com.me.guanpj.mall.provider.PushProvider
import com.me.guanpj.mall.provider.router.RouterPath
import com.me.guanpj.mall.user.R
import com.me.guanpj.mall.user.module.forgetpwd.ForgetPwdActivity
import com.me.guanpj.mall.user.module.register.RegisterActivity
import com.me.guanpj.mall.user.util.UserPrefsUtils
import kotlinx.android.synthetic.main.activity_login.*
import org.jetbrains.anko.startActivity
import org.jetbrains.anko.toast

/*
  登录界面
 */
@Route(path = RouterPath.UserCenter.PATH_LOGIN)
class LoginActivity : BaseMvpActivity<LoginPresenter>(), LoginContract.View, View.OnClickListener {

    @Autowired(name = RouterPath.MessageCenter.PATH_MESSAGE_PUSH)
    @JvmField
    var mPushProvider: PushProvider? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

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
        mLoginBtn.enable(mMobileEt, { isBtnEnable() })
        mLoginBtn.enable(mPwdEt, { isBtnEnable() })

        mLoginBtn.onClick(this)
        mHeaderBar.getRightView().onClick(this)
        mForgetPwdTv.onClick(this)
    }

    /*
      点击事件
     */
    override fun onClick(view: View) {
        when (view.id) {
            R.id.mRightTv -> {
                startActivity<RegisterActivity>()
            }

            R.id.mLoginBtn -> {
                mPresenter.login(mMobileEt.text.toString(), mPwdEt.text.toString(), mPushProvider?.getPushId()
                        ?: "")
            }
            R.id.mForgetPwdTv -> {
                startActivity<ForgetPwdActivity>()
            }
        }
    }

    /*
      判断按钮是否可用
     */
    private fun isBtnEnable(): Boolean {
        return mMobileEt.text.isNullOrEmpty().not() &&
                mPwdEt.text.isNullOrEmpty().not()
    }

    /*
      登录回调
     */
    override fun onLoginResult(result: UserInfo) {
        toast("登录成功")
        UserPrefsUtils.putUserInfo(result)
        finish()
    }
}
