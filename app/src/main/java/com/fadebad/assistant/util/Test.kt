package com.fadebad.assistant.util

import android.util.Log
import java.io.*

object Test {
    const val TAG = "ShellUtils"

    @Synchronized
    fun execShellStr(cmd: String): String {
        Log.d(TAG, "execShellStr: $cmd")
        val cmdStrings = arrayOf(
                "sh", "-c", cmd
        )
        var retString = ""
        try {
            val process = Runtime.getRuntime().exec(cmdStrings)
            val stdout = BufferedReader(InputStreamReader(
                    process.inputStream), 7777)
            val stderr = BufferedReader(InputStreamReader(
                    process.errorStream), 7777)
            var line: String? = null
            while (null != stdout.readLine().also { line = it }
                    || null != stderr.readLine().also { line = it }) {
                if ("" !== line) {
                    retString += """
                        $line
                        
                        """.trimIndent()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return retString
    }

    @Synchronized
    fun readFile(path: String): String {
        val file = File(path)
        var str = ""
        var reader: BufferedReader? = null
        try {
            reader = BufferedReader(FileReader(file))
            var line: String? = null
            while (reader.readLine().also { line = it } != null) {
                str = str + line
            }
        } catch (e: Exception) {
            Log.d(TAG, "Read file error!!!")
            str = "readError"
            e.printStackTrace()
        } finally {
            if (reader != null) {
                try {
                    reader.close()
                } catch (e2: Exception) {
                    e2.printStackTrace()
                }
            }
        }
        Log.d(TAG, "read " + path + " value is " + str.trim { it <= ' ' })
        return str.trim { it <= ' ' }
    }

    @Synchronized
    fun writeFile(path: String, cmd: String) {
        Log.d(TAG, "path: $path; cmd: $cmd")
        val file = File(path)
        if (!file.exists()) {
            Log.d(TAG, "the file is not exists")
            return
        }
        var fos: FileOutputStream? = null
        var bos: BufferedOutputStream? = null
        try {
            fos = FileOutputStream(file)
            bos = BufferedOutputStream(fos)
            val bytes = cmd.toByteArray()
            bos.write(bytes)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            if (bos != null) {
                try {
                    bos.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            if (fos != null) {
                try {
                    fos.close()
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}