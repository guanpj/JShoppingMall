package com.me.guanpj.mall.user.service

import rx.Observable

/*
  上传业务接口
 */
interface UploadService {
    fun getUploadToken(): Observable<String>
}
