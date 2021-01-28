package com.fadebad.assistant.ui.home

import android.content.Context
import android.os.*
import android.provider.Settings
import android.service.oemlock.OemLockManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.fadebad.assistant.R
import com.fadebad.assistant.util.ShellUtils
import com.freeme.util.FreemeFeature

class HomeFragment : Fragment() {
    private val TAG = "HomeFragment"

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var homeAdapter: HomeAdapter
    private lateinit var homeLayoutManager: GridLayoutManager

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
                ViewModelProvider(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)
        homeRecyclerView = root.findViewById(R.id.homeRecyclerView)
        setupRecyclerView()

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

    private fun setupRecyclerView() {
        homeAdapter = HomeAdapter(homeViewModel)
        homeLayoutManager = GridLayoutManager(context, 3)
        homeRecyclerView.adapter = homeAdapter
        homeRecyclerView.layoutManager = homeLayoutManager
        homeLayoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (homeViewModel.shortCuts.value!![position].type) {
                    0 -> 3
                    1 -> 1
                    2 -> 1
                    else -> 3
                }
            }
        }
    }


    override fun onResume() {
        super.onResume()
        homeViewModel.getShortCuts()
    }
}