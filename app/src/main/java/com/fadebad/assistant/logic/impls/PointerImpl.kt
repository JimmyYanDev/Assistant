package com.fadebad.assistant.logic.impls

import android.provider.Settings

class PointerImpl : SettingsImpl() {
    override fun getTable(): Table = Table.SYSTEM

    override fun getName(): String = Settings.System.POINTER_LOCATION
}