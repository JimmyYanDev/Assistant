package com.fadebad.assistant.logic.impls

import android.content.Context
import android.os.Build
import android.provider.Settings
import com.fadebad.assistant.logic.`interface`.ShortCutFunc

abstract class SettingsImpl : ShortCutFunc {
    override fun <T> get(context: Context): T {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val name = getName()
            val result = when (getTable()) {
                Table.SYSTEM -> {
                    Settings.System.getString(context.contentResolver, name)
                }
                Table.SECURE -> {
                    Settings.Secure.getString(context.contentResolver, name)
                }
                Table.GLOBAL -> {
                    Settings.Global.getString(context.contentResolver, name)
                }
            }
            return if (result == "1") true as T else false as T
        } else {
            return false as T
        }
    }

    override fun <T> set(context: Context, value: T) {
        if (value is Boolean && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            val name = getName()
            when (getTable()) {
                Table.SYSTEM -> {
                    Settings.System.putInt(context.contentResolver, name, if (value) 1 else 0)
                }
                Table.SECURE -> {
                    Settings.Secure.putInt(context.contentResolver, name, if (value) 1 else 0)
                }
                Table.GLOBAL -> {
                    Settings.Global.putInt(context.contentResolver, name, if (value) 1 else 0)
                }
            }
        }
    }

    abstract fun getTable() : Table

    abstract fun getName() : String

    enum class Table {
        SYSTEM, SECURE, GLOBAL
    }
}