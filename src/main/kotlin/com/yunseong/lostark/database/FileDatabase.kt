package com.yunseong.lostark.database

import java.io.File

object FileDatabase {

    fun append(fileName: String, content: String) {
        val file = File(fileName)
        if (!file.exists()) {
            file.createNewFile()
        }
        file.appendText(content)
    }

    fun read(fileName: String): List<String> {
        val file = File(fileName)
        if (!file.exists()) {
            return emptyList()
        }
        return file.readLines()
    }
}