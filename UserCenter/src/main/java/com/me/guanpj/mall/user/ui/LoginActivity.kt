package com.me.guanpj.mall.user.ui

import android.app.Activity
import android.os.Bundle
import android.widget.TextView

class LoginActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val textview: TextView = TextView(this)
        textview.setText("ldjflds")
        setContentView(textview)
    }
}