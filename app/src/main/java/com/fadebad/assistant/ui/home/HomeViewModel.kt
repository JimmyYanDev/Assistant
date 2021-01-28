package com.fadebad.assistant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fadebad.assistant.R
import com.fadebad.assistant.logic.impls.AdbImpl
import com.fadebad.assistant.logic.home.model.ShortCut
import com.fadebad.assistant.logic.impls.PointerImpl
import com.fadebad.assistant.logic.impls.TouchImpl

class HomeViewModel : ViewModel() {
    private val _shortCuts = MutableLiveData<List<ShortCut>>().apply {
        value = emptyList()
    }

    val shortCuts: LiveData<List<ShortCut>>
        get() = _shortCuts

    fun getShortCuts(){
        val shortCutsToShow = ArrayList<ShortCut>()
        var times = (1..10).random()
        var flag = 0
        shortCutsToShow.add(ShortCut(0, "adb", R.drawable.ic_adb, 2, 1, AdbImpl()))
        shortCutsToShow.add(ShortCut(0, "touch dot", R.drawable.ic_touch, 2, 1, TouchImpl()))
        shortCutsToShow.add(ShortCut(0, "pointer", R.drawable.ic_pointer, 2, 1, PointerImpl()))
        shortCutsToShow.add(ShortCut(0, "divider", 0, 2, 3, null))
        shortCutsToShow.add(ShortCut(0, "Title1", 0, 2, 0, null))
        do {
            flag++
            shortCutsToShow.add(ShortCut(0, "switch$flag", 0, 2, 1, null))
        } while (flag <= times)

        shortCutsToShow.add(ShortCut(0, "divider", 0, 2, 3, null))
        shortCutsToShow.add(ShortCut(0, "Title2", 0, 2, 0, null))
        times = (1..10).random()
        flag = 0
        do {
            flag++
            shortCutsToShow.add(ShortCut(0, "intent$flag", 0, 2, 2, null))
        } while (flag <= times)
        _shortCuts.value = shortCutsToShow
    }

}