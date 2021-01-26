package com.fadebad.assistant

import android.app.Application
import androidx.multidex.MultiDexApplication
import com.didichuxing.doraemonkit.DoraemonKit

class AssistantApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        DoraemonKit.install(this)
    }
}