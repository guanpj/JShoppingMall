package com.kotlin.user.injection.module

import com.me.guanpj.mall.user.service.UploadService
import com.me.guanpj.mall.user.service.impl.UploadServiceImpl
import dagger.Module
import dagger.Provides

/*
  上传Module
 */
@Module
class UploadModule {

    @Provides
    fun provideUploadService(uploadService: UploadServiceImpl): UploadService {
        return uploadService
    }
}
