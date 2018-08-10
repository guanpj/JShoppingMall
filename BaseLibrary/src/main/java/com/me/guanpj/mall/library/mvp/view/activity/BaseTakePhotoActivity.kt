package com.me.guanpj.mall.library.mvp.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import com.alibaba.android.arouter.launcher.ARouter
import com.bigkoo.alertview.AlertView
import com.bigkoo.alertview.OnItemClickListener
import com.jph.takephoto.app.TakePhoto
import com.jph.takephoto.app.TakePhotoImpl
import com.jph.takephoto.compress.CompressConfig
import com.jph.takephoto.model.TResult
import com.me.guanpj.mall.library.core.BaseApplication
import com.me.guanpj.mall.library.di.component.ActivityComponent
import com.me.guanpj.mall.library.di.component.DaggerActivityComponent
import com.me.guanpj.mall.library.di.module.ActivityModule
import com.me.guanpj.mall.library.di.module.LifecycleProviderModule
import com.me.guanpj.mall.library.mvp.IBaseView
import com.me.guanpj.mall.library.mvp.presenter.BasePresenter
import com.me.guanpj.mall.library.util.DateUtils
import com.me.guanpj.mall.library.widget.ProgressLoading
import org.jetbrains.anko.toast
import java.io.File
import javax.inject.Inject

/*
  存在选择图片的Activity基础封装
 */
abstract class BaseTakePhotoActivity<P : BasePresenter<*>> : BaseActivity(), IBaseView, TakePhoto.TakeResultListener {

    @Inject
    lateinit var mPresenter: P

    lateinit var mActivityComponent: ActivityComponent

    private lateinit var mTakePhoto: TakePhoto

    private lateinit var mTempFile: File

    private lateinit var mLoadingDialog: ProgressLoading

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActivityInjection()
        performInject()

        mPresenter.onAttach(this)

        mTakePhoto = TakePhotoImpl(this, this)
        mTakePhoto.onCreate(savedInstanceState)

        mLoadingDialog = ProgressLoading.create(this)
        ARouter.getInstance().inject(this)
    }

    fun initActivityInjection() {
        mActivityComponent = DaggerActivityComponent.builder().appComponent(BaseApplication.appComponent)
                .activityModule(ActivityModule(this))
                .lifecycleProviderModule(LifecycleProviderModule(this))
                .build()
    }

    abstract fun performInject()

    override fun showLoading() = mLoadingDialog.showLoading()

    override fun hideLoading() = mLoadingDialog.hideLoading()

    override fun onError(text: String) = toast(text)

    /*
      弹出选择框，默认实现 可根据实际情况，自行修改
     */
    protected fun showAlertView() {
        AlertView("选择图片", "", "取消", null, arrayOf("拍照", "相册"), this,
                AlertView.Style.ActionSheet, OnItemClickListener { _, position ->
            mTakePhoto.onEnableCompress(CompressConfig.ofDefaultConfig(), false)
            when (position) {
                0 -> {
                    createTempFile()
                    mTakePhoto.onPickFromCapture(Uri.fromFile(mTempFile))
                }
                1 -> mTakePhoto.onPickFromGallery()
            }
        }).show()
    }

    /*
      获取图片，成功回调
     */
    override fun takeSuccess(result: TResult?) {
        Log.d("TakePhoto", result?.image?.originalPath)
        Log.d("TakePhoto", result?.image?.compressPath)
    }

    /*
      获取图片，取消回调
     */
    override fun takeCancel() {
    }

    /*
      获取图片，失败回调
     */
    override fun takeFail(result: TResult?, msg: String?) {
        Log.e("takePhoto", msg)
    }

    /*
       akePhoto默认实现
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        mTakePhoto.onActivityResult(requestCode, resultCode, data)
    }

    /*
      新建临时文件
     */
    fun createTempFile() {
        val tempFileName = "${DateUtils.curTime}.png"
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            this.mTempFile = File(Environment.getExternalStorageDirectory(), tempFileName)
            return
        }

        this.mTempFile = File(filesDir, tempFileName)
    }
}
