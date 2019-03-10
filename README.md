<h1 align="center">JShoppingMall</h1>

**项目介绍：**

一款商城购物 App，商品数据采用 Python 爬虫爬取自某小型电商平台，服务端部署在腾讯云。项目主要分为：主页、分类、购物车、消息、和“我的”模块。

## 特点

- [x] 采用 Kotlin 语言进行开发
- [x] 采用 MVP + dagger 2 架构
- [x] 组件化开发，减少模块间的耦合
- [x] 采用 Retrofit + RxKotlin 进行网络请求
- [x] 使用 ARouter 进行页面路由和 EventBus 进行全局事件广播

## 项目预览

| 主页 | 分类 | 购物车 |
|:-:|:-:|:-:|
| ![1](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/1main.png) | ![2](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/2category.png) | ![3](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/3cart.png) |

| 消息 | 我的 | 商品列表 |
|:-:|:-:|:-:|
| ![4](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/4message.png) | ![5](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/5mine.png) | ![6](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/6good_list.png) |

| 商品详情 | 订单 | 支付 |
|:-:|:-:|:-:|
| ![7](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/7good_detail.png) | ![8](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/8order.png) | ![9](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/9pay.png) |

| 地址管理 | 新增地址 | 设置 |
|:-:|:-:|:-:|
| ![10](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/10address_list.png) | ![11](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/11address_new.png) | ![12](https://my-bucket-1251125515.cos.ap-guangzhou.myqcloud.com/JShoppingMall/12setting.png) |

## 使用到的第三方开源库

项目名称 | 简介
  -------- | ------
[RxKotlin](https://github.com/ReactiveX/RxKotlin) | RxJava bindings for Kotlin
[RxAndroid](https://github.com/ReactiveX/RxAndroid) | 在 Android 中使用 RxJava
[ARouter](https://github.com/alibaba/ARouter) | 帮助 Android App 进行组件化改造的路由框架
[Retrofit](https://github.com/square/retrofit) | HTTP 请求工具库
[OkHttp](https://github.com/square/okhttp) | 适用于 Android 和 Java 应用的网络请求框架
[Dagger](https://github.com/google/dagger) | 强大的依赖注入框架
[Glide](https://github.com/bumptech/glide) |  好用的图片加载框架
[CircleImageView](https://github.com/hdodenhof/CircleImageView) | 圆形图片不复杂
[MultiStateView](https://github.com/Kennyc1012/MultiStateView) | 通用状态切换视图
[RxBus](https://github.com/Dimezis/RxBus) | 适用于 Kotlin 的 RxBus
[FlowLayout](https://github.com/Kennyc1012/MultiStateView) | Android 流式布局，支持单选、多选等，适合用于产品标签等
[leakcanary](https://github.com/square/leakcanary) | 使用于 Android 和 Java 和内存泄漏检查工具
[BadgeView](https://github.com/qstumn/BadgeView) | 支持自由定制外观、拖拽消除的 MaterialDesign 风格 Android BadgeView

## 使用到的工具/网站

网站名称 | 简介
  -------- | ------
[Iconfont](http://www.iconfont.cn/) | 阿里巴巴矢量图标库，提供了本项目中的大部分矢量图
[aconvert](https://www.aconvert.com/cn/image/resize/) | 在线调整图片大小（PNG, JPG 和 GIF）
[convertio](https://convertio.co/zh/png-converter/) | 在线图像文件转换器，支持 SVG 转换成 PNG
[logoko](http://www.logoko.com.cn/design) | 在线 logo 设计
[图帮主](http://www.tubangzhu.com/) | 在线平面设计
[shields](https://shields.io/) | metadata 图标设计
[compresspng](https://compresspng.com/zh/) | 在线压缩 PNG 图像

## License
> Copyright (C) 2018 guanpj.
> Licensed under the [GPL-3.0](https://www.gnu.org/licenses/gpl.html).
> (See the [LICENSE](https://github.com/guanpj/JShoppingMall/blob/master/LICENSE) file for the whole license text)
