package com.me.guanpj.mall.meassage.di.component


import com.kotlin.message.ui.fragment.MessageFragment
import com.me.guanpj.mall.library.di.component.FragmentComponent
import com.me.guanpj.mall.library.di.scope.ComponentScope
import com.me.guanpj.mall.meassage.di.module.MessageModule
import dagger.Component

/*
  消息模块注入组件
 */
@ComponentScope
@Component(dependencies = arrayOf(FragmentComponent::class), modules = arrayOf(MessageModule::class))
interface MessageComponent {
    fun inject(fragment: MessageFragment)
}
