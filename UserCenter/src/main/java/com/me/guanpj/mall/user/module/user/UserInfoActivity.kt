package com.me.guanpj.mall.user.module.user

import android.os.Bundle
import android.util.Log
import com.jph.takephoto.model.TResult
import com.kotlin.base.common.BaseConstant
import com.kotlin.user.data.protocol.UserInfo
import com.kotlin.user.injection.component.DaggerUserComponent
import com.kotlin.user.injection.module.UserModule
import com.me.guanpj.mall.library.ext.onClick
import com.me.guanpj.mall.library.mvp.view.activity.BaseTakePhotoActivity
import com.me.guanpj.mall.library.util.AppPrefsUtils
import com.me.guanpj.mall.library.util.GlideUtils
import com.me.guanpj.mall.provider.common.ProviderConstant
import com.me.guanpj.mall.user.R
import com.me.guanpj.mall.user.util.UserPrefsUtils
import com.qiniu.android.http.ResponseInfo
import com.qiniu.android.storage.UpCompletionHandler
import com.qiniu.android.storage.UploadManager
import kotlinx.android.synthetic.main.activity_user_info.*
import org.jetbrains.anko.toast
import org.json.JSONObject

/*
    用户信息
 */
class UserInfoActivity : BaseTakePhotoActivity<UserInfoPresenter>(), UserInfoContract.View {


    private val mUploadManager: UploadManager by lazy { UploadManager() }

    private var mLocalFileUrl: String? = null
    private var mRemoteFileUrl: String? = null

    private var mUserIcon: String? = null
    private var mUserName: String? = null
    private var mUserMobile: String? = null
    private var mUserGender: String? = null
    private var mUserSign: String? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter.onAttach(this)
        setContentView(R.layout.activity_user_info)

        initView()
        initData()
    }

    override fun performInject() {
        DaggerUserComponent.builder().activityComponent(mActivityComponent)
                .userModule(UserModule()).build().inject(this)
    }

    /*
      初始化视图
     */
    private fun initView() {
        mUserIconView.onClick {
            showAlertView()
        }

        mHeaderBar.getRightView().onClick {
            mPresenter.editUser(mRemoteFileUrl!!,
                    mUserNameEt.text?.toString() ?: "",
                    if (mGenderMaleRb.isChecked) "0" else "1",
                    mUserSignEt.text?.toString() ?: ""
            )
        }
    }

    /*
        初始化数据
     */
    private fun initData() {
        mUserIcon = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_ICON)
        mUserName = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_NAME)
        mUserMobile = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_MOBILE)
        mUserGender = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_GENDER)
        mUserSign = AppPrefsUtils.instant.getString(ProviderConstant.KEY_SP_USER_SIGN)

        mRemoteFileUrl = mUserIcon
        if (mUserIcon != "") {
            GlideUtils.loadUrlImage(this, mUserIcon!!, mUserIconIv)
        }
        mUserNameEt.setText(mUserName)
        mUserMobileTv.text = mUserMobile

        if (mUserGender == "0") {
            mGenderMaleRb.isChecked = true
        } else {
            mGenderFemaleRb.isChecked = true
        }

        mUserSignEt.setText(mUserSign)
    }

    /*
      获取图片成功回调
     */
    override fun takeSuccess(result: TResult?) {
        mLocalFileUrl = result?.image?.compressPath
        mPresenter.getUploadToken()
    }


    /*
      获取上传凭证回调
     */
    override fun onGetUploadTokenResult(result: String) {
        mUploadManager.put(mLocalFileUrl, null, result, object : UpCompletionHandler {
            override fun complete(key: String?, info: ResponseInfo?, response: JSONObject?) {
                mRemoteFileUrl = BaseConstant.IMAGE_SERVER_ADDRESS + response?.get("hash")

                Log.d("test", mRemoteFileUrl)
                GlideUtils.loadUrlImage(this@UserInfoActivity, mRemoteFileUrl!!, mUserIconIv)
            }

        }, null)
    }

    /*
      编辑用户资料回调
     */
    override fun onEditUserResult(result: UserInfo) {
        toast("修改成功")
        UserPrefsUtils.putUserInfo(result)
    }
}
