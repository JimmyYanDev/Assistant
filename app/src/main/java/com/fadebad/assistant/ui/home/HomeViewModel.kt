package com.fadebad.assistant.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.fadebad.assistant.logic.home.model.ShortCut

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
        shortCutsToShow.add(ShortCut(0, "divider", "", 2, 3))
        shortCutsToShow.add(ShortCut(0, "Title1", "", 2, 0))
        do {
            flag++
            shortCutsToShow.add(ShortCut(0, "switch$flag", "", 2, 1))
        } while (flag <= times)

        shortCutsToShow.add(ShortCut(0, "divider", "", 2, 3))
        shortCutsToShow.add(ShortCut(0, "Title2", "", 2, 0))
        times = (1..10).random()
        flag = 0
        do {
            flag++
            shortCutsToShow.add(ShortCut(0, "intent$flag", "", 2, 2))
        } while (flag <= times)
        _shortCuts.value = shortCutsToShow
    }

}