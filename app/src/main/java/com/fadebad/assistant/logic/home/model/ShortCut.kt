package com.fadebad.assistant.logic.home.model

import com.fadebad.assistant.logic.`interface`.ShortCutFunc

data class ShortCut(val id:  Int = 0, val label: String, val icon:Int, val category: Int, val type: Int, val funcImpl: ShortCutFunc?) {
}