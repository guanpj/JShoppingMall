package com.me.guanpj.mall.library.widget

import android.content.Context
import android.widget.ImageView
import com.me.guanpj.mall.library.util.GlideUtils
import com.youth.banner.loader.ImageLoader

class BannerImageLoader : ImageLoader() {
    override fun displayImage(context: Context, path: Any, imageView: ImageView) {
        GlideUtils.loadUrlImage(context, path.toString(), imageView)
    }
}
