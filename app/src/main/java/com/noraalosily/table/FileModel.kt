package com.noraalosily.table

data class FilePreview(var fileName:String, var  fileDescription: String)

class FileModel {

    private var files: ArrayList<FilePreview> = ArrayList()

    init {

        files.add(FilePreview("file a", "some description"))
        files.add(FilePreview("file b", "some description"))
    }

    fun get(index: Int):String = files[index].fileName
    fun size():Int = files.size


}