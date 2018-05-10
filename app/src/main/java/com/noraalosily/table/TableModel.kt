package com.noraalosily.table

data class TableStructure(var x:Double, var  y: Double)

class TableModel {

    private var files: ArrayList<TableStructure> = ArrayList()

    init {

        files.add(TableStructure(1.0, 1.0))
        files.add(TableStructure(2.0, 2.0))
        files.add(TableStructure(1.0, 1.0))
        files.add(TableStructure(2.0, 2.0))
        files.add(TableStructure(1.0, 1.0))
        files.add(TableStructure(2.0, 2.0))
        files.add(TableStructure(1.0, 1.0))
        files.add(TableStructure(2.0, 111.0))
        files.add(TableStructure(44.0, 1.0))
        files.add(TableStructure(2.0, 2.0))
        files.add(TableStructure(1.0, 44.0))
        files.add(TableStructure(2.0, 2.0))
        files.add(TableStructure(4443.0, 1.0))
        files.add(TableStructure(3435.0, 2.0))
        files.add(TableStructure(1.0, 1.0))
        files.add(TableStructure(2.0, 4532.0))
    }

    fun getX(index: Int):String = files[index].x.toString()
    fun getY(index: Int):String = files[index].y.toString()

    fun size():Int = files.size


}