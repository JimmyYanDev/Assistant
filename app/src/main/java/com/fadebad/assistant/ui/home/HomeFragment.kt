package com.fadebad.assistant.ui.home

import android.os.Build
import android.os.Bundle
import android.os.SystemProperties
import android.provider.Settings
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.freeme.util.FreemeFeature
import com.fadebad.assistant.R
import com.fadebad.assistant.util.ShellUtils

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        val textView: TextView = root.findViewById(R.id.text_home)
        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        Log.d(TAG, "onCreateView: " + FreemeFeature.get("text-replace", "ext.replace.packages"))
        Log.d(TAG, "onCreateView: " + SystemProperties.get("vendor.cam.sensor.info"))
        Log.d(TAG, "onCreateView: " + ShellUtils.execShellStr("setprop service.adb.tcp.port 5555"))
        //SystemProperties.set("service.adb.tcp.port", "6666")
        val btn = root.findViewById<Button>(R.id.developmentSettingsControllerBtn)
        btn.setOnClickListener {
            context?.apply {
                var last = Settings.Global.getInt(contentResolver, Settings.Global.ADB_ENABLED)
                Log.d(TAG, "onCreateView: adb=$last")
                last = 1 -last
                Settings.Global.putInt(contentResolver, Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, last)
                Settings.Global.putInt(contentResolver, Settings.Global.ADB_ENABLED, last)
                Toast.makeText(context, (if (last==0) "关闭" else "开启"), Toast.LENGTH_SHORT).show()
            }
        }
        return root
    }
}