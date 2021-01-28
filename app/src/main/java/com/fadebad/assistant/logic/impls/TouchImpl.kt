package com.fadebad.assistant.logic.impls

import android.provider.Settings

class TouchImpl : SettingsImpl() {
    override fun getTable(): Table = Table.SYSTEM

    override fun getName(): String = Settings.System.SHOW_TOUCHES
}