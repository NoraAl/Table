package com.noraalosily.table

import android.content.Context
import android.util.Log
import java.io.*
import java.text.DateFormat
import java.util.*

data class Point(var x:Double, var  y: Double):Serializable

class TableModel: Serializable {
    private var table: ArrayList<Point> = ArrayList()
    private lateinit var meta: FileMeta
    private var context: Context? = null

    constructor(){
        val timesMillis = System.currentTimeMillis()
        val date = DateFormat.getDateInstance().format(Date(timesMillis))
        val time = DateFormat.getTimeInstance().format(Date(timesMillis))

        val name = "table $date $time"

        meta = FileMeta(name,date,time)



        var temp = Point(0.0,0.0)
        for (i in 0 until
                1){
            temp = Point(i.toDouble(), i.toDouble())
            table.add(temp)
        }
    }
    constructor(values: ArrayList<Point>, name: String){}

    fun setContext(context: Context){this.context = context}



    fun getX(index: Int):String = table[index].x.toString()
    fun getY(index: Int):String = table[index].y.toString()

    fun setX(index: Int, value: Double) {table[index].x = value}
    fun setY(index: Int,  value: Double) {table[index].y = value}

    fun get(index: Int):String = table[index].x.toString() + ", " + table[index].y.toString()
    fun set(index: Int,  x: Double, y: Double) {table[index].x = x; table[index].y = y}

    fun delete(i : Int){table.removeAt(i)}

    fun size():Int = table.size

    fun getName(): String = meta.filename
    fun setName(name: String) {this.meta.filename = name}

    fun getMeta():FileMeta = meta
    fun setMeta(meta: FileMeta) {this.meta = meta}

    fun add():Int{
        table.add(Point(0.0,0.0))
        return table.lastIndex
    }

    fun save(){

        var obj: FileModel? = null
        val file = File(context?.filesDir, InitialView.TABLE_FILES_NAME)
        if (!file.exists())
            Log.e("SSSSS","sssss")


        FileOutputStream(meta.filename).use {
            ObjectOutputStream(it).apply {

                writeObject(this)
                close()
            }
            it.close()
        }
    }



    // print access
    fun print(){
        var str = ""
        //for (point in table) println("${point.x},${point.y}")
        for (point in table) str += "${point.x},${point.y}––––"

        Log.e("TableModel---", meta.filename)
        Log.e("TableModel---", str)
    }

    fun print(i: Int){
        Log.e("Table", "${table[i].x},${table[i].y}")
    }



}