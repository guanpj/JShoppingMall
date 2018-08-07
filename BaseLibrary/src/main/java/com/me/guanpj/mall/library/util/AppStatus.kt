package com.me.guanpj.mall.library.util

class AppStatus {
    companion object {
        val STATUS_FORCE_KILLED = -1
        val STATUS_LOGOUT = 0
        val STATUS_OFFLINE = 1
        val STATUS_ONLINE = 2
        val STATUS_KICK_OUT = 3

        val KEY_HOME_ACTION = "key_home_action"
        val ACTION_BACK_TO_HOME = 0
        val ACTION_RESTART_APP = 1
        val ACTION_LOGOUT = 2
        val ACTION_KICK_OUT = 3
    }
}