package com.me.guanpj.mall.user.service.impl

import com.me.guanpj.mall.library.ext.convert
import com.me.guanpj.mall.user.data.repository.UploadRepository
import com.me.guanpj.mall.user.service.UploadService
import rx.Observable
import javax.inject.Inject

/*
  上传业务实现类
 */
class UploadServiceImpl @Inject constructor(): UploadService {

    @Inject
    lateinit var repository: UploadRepository

    override fun getUploadToken(): Observable<String> {
       return repository.getUploadToken().convert()
    }
}
