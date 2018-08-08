package com.me.guanpj.mall.user.data.repository

import com.me.guanpj.mall.library.data.BaseResp
import com.me.guanpj.mall.library.data.net.RetrofitFactory
import com.me.guanpj.mall.user.data.api.UploadApi
import rx.Observable
import javax.inject.Inject

/*
  上传相关 数据层
 */
class UploadRepository @Inject constructor() {
    /*
      获取七牛云上传凭证
     */
    fun getUploadToken(): Observable<BaseResp<String>> {
        return RetrofitFactory.instance.create(UploadApi::class.java).getUploadToken()
    }


}
