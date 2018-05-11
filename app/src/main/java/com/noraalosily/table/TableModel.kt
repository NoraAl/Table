package com.noraalosily.table

import android.util.Log

data class Point(var x:Double, var  y: Double)

class TableModel {

    private var table: ArrayList<Point> = ArrayList()
    private lateinit var name: String
    constructor(){
        val timeFileCreatedInMillis = System.currentTimeMillis()
        name = "table_"+timeFileCreatedInMillis.toString()
        var temp = Point(0.0,0.0)
        for (i in 0 until
                1){
            temp = Point(i.toDouble(), i.toDouble())
            table.add(temp)
        }
    }
    constructor(values: ArrayList<Point>, name: String){}



    fun getX(index: Int):String = table[index].x.toString()
    fun getY(index: Int):String = table[index].y.toString()

    fun setX(index: Int, value: Double) {table[index].x = value}
    fun setY(index: Int,  value: Double) {table[index].y = value}

    fun get(index: Int):String = table[index].x.toString() + ", " + table[index].y.toString()
    fun set(index: Int,  x: Double, y: Double) {table[index].x = x; table[index].y = y}

    fun delete(i : Int){table.removeAt(i)}

    fun size():Int = table.size

    fun getName(): String = name
    fun setName(name: String) {this.name = name}

    fun add():Int{
        table.add(Point(0.0,0.0))
        return table.lastIndex
    }



    ///print
    fun print(){
        var str = ""
        //for (point in table) println("${point.x},${point.y}")
        for (point in table) str += "${point.x},${point.y}––––"

        Log.e("TableModel---", name)
        Log.e("TableModel---", str)
    }

    fun print(i: Int){
        Log.e("Table", "${table[i].x},${table[i].y}")
    }


}