package com.fadebad.assistant.util

import android.util.Log
import java.io.BufferedReader
import java.io.InputStreamReader

object ShellUtils {
    val TAG = "ShellUtils"

    fun execShellStr(cmd: String): String {
        val cmdStrings =  arrayOf(
                "sh", "-c", cmd
        )
        Log.d(TAG, "execShellStr: " + cmdStrings);
        var retString = ""
        try {
            val process = Runtime.getRuntime().exec(cmdStrings)
            val stdout = BufferedReader(InputStreamReader(process.inputStream), 7777)
            val stderr = BufferedReader(InputStreamReader(process.errorStream), 7777)
            var line: String? = null
            while (null != stdout.readLine().also { line = it }
                    || null != stderr.readLine().also { line = it }) {
                line?.apply {
                    retString += this + "\n"
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retString
    }
}