package com.me.guanpj.mall.user.data.api

import com.me.guanpj.mall.library.data.BaseResp
import retrofit2.http.POST
import rx.Observable

/*
  上传相关 接口
 */
interface UploadApi {

    /*
      获取七牛云上传凭证
     */
    @POST("common/getUploadToken")
    fun getUploadToken(): Observable<BaseResp<String>>
}
