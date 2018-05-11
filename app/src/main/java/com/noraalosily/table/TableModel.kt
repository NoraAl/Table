package com.noraalosily.table

import android.util.Log

data class Point(var x:Double, var  y: Double)

class TableModel {

    private var table: ArrayList<Point> = ArrayList()
    constructor(){
        var temp = Point(0.0,0.0)
        for (i in 0..20){
            temp = Point(i.toDouble(), i.toDouble())
            table.add(temp)
        }
    }
    constructor(values: ArrayList<Point>){}

    fun print(){
        for (point in table) println("${point.x},${point.y}")
    }

    fun getX(index: Int):String = table[index].x.toString()
    fun getY(index: Int):String = table[index].y.toString()

    fun setX(index: Int, value: Double) {table[index].x = value}
    fun setY(index: Int,  value: Double) {table[index].y = value}

    fun get(index: Int):String = table[index].x.toString() + ", " + table[index].y.toString()
    fun set(index: Int,  x: Double, y: Double) {table[index].x = x; table[index].y = y}

    fun delete(i : Int){table.removeAt(i)}

    fun size():Int = table.size


}