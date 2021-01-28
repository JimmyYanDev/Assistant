package com.fadebad.assistant.logic.impls

import android.provider.Settings

class AdbImpl : SettingsImpl() {

    override fun getTable(): Table = Table.GLOBAL

    override fun getName(): String = Settings.Global.ADB_ENABLED
}