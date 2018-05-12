package com.noraalosily.table

import java.io.Serializable

data class FileMeta(var filename:String, var  date: String, var time: String, var id: String =""):Serializable

class FileModel:Serializable {


    private var files: ArrayList<FileMeta> = ArrayList()

    //fun get(index: Int):String = files[index].filename
    fun size():Int = files.size
    fun isEmpty():Boolean = files.isEmpty()

    fun addFile(file: FileMeta){files.add(file)}
    fun getFile(index: Int): FileMeta = files[index]
    fun resetFile(index: Int, fileMeta: FileMeta){files[index] = fileMeta}

    fun getFilename(index: Int) = files[index].filename
    fun getDate(index: Int) = files[index].date
    fun getTime(index: Int) = files[index].time
    fun getId(index: Int) = files[index].id

    fun setFilename(index: Int, name: String ){ files[index].filename = name}
    fun setDate(index: Int, date: String ){ files[index].date = date}
    fun setTime(index: Int, time: String ){ files[index].time = time}
    fun setId(index: Int, id: String ){ files[index].id = id}



    fun exist(filename: String):Boolean{
        for (file in files)
            if (file.filename == filename)
                return true

        return false
    }

    fun idExist(id: String):Boolean{
        for (file in files)
            if (file.id == id)
                return true

        return false
    }

    fun getPosition(id: String):Int{
        for ((position, file) in files.withIndex()){
            if (file.id == id)
                return position
        }
        return -1
    }


}