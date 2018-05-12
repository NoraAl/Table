package com.noraalosily.table

import java.io.Serializable

data class FileMeta(var filename:String, var  date: String, var time: String):Serializable

class FileModel:Serializable {

    private var files: ArrayList<FileMeta> = ArrayList()

    init {
        val f = FileMeta("dd","dd","dd")

        files.add(f)
        files.add(f)
    }

    fun get(index: Int):String = files[index].filename
    fun size():Int = files.size

    fun getFilename(index: Int) = files[index].filename
    fun getDate(index: Int) = files[index].date
    fun getTime(index: Int) = files[index].time

    fun setFilename(index: Int, name: String ){ files[index].filename = name}
    fun setDate(index: Int, date: String ){ files[index].date = date}
    fun setTime(index: Int, time: String ){ files[index].time = time}


}