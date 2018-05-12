package com.noraalosily.table


import android.app.Fragment
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import java.io.*


class TableRecyclerFragment() : Fragment(), TableAdapter.AdapterListener {
    companion object {
        private const val TAG = "TableRecyclerFragment"
    }

    private var recyclerView: RecyclerView? = null
    private var tableAdapter: RecyclerView.Adapter<*>? = null
    private var table = TableModel()
    private lateinit var viewManager: LinearLayoutManager

//    init {
//        tableAdapter = TableAdapter(table, this)
//
//    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = LinearLayoutManager(context)

    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        val id = arguments.getString(TableView.TABLE_ID)
        Log.e(TAG, "-------------------------------  $id")
        if (!id.isEmpty())
            table = openTable(id)
        tableAdapter = TableAdapter(table, this)
    }


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView =  inflater?.inflate(R.layout.table_recycler_view, container, false)
        recyclerView = fragmentView?.findViewById(R.id.tableRecyclerView)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = viewManager
        recyclerView?.adapter = tableAdapter

        return fragmentView
    }

    fun changeTableName(name: String){
        if (name.isEmpty()) return
        table.setName(name)
    }

    fun getTableName():String =  table.getName()
    fun getTable():TableModel  = table

    fun newValue(){
        val adapter = (tableAdapter as TableAdapter)
        val position = table.add()
        adapter.notifyDataSetChanged()
        table.print()
        viewManager.scrollToPosition(position)
    }

    private fun openTable(filename: String):TableModel{
        var obj: TableModel? = null

        val file = File(context.filesDir, filename)
        if (!file.exists())
            return TableModel()

        FileInputStream(file).use {
            ObjectInputStream(it).apply {
                obj = readObject() as TableModel
                close()
            }
            it.close()
        }

        return obj?: TableModel()
    }

    override fun scrollToTop(position: Int) {
        val firstPosition = viewManager.findFirstVisibleItemPosition()
        val firstItemView = viewManager.findViewByPosition(firstPosition)
        val offset = firstItemView.top

        viewManager.scrollToPositionWithOffset(position, offset)
        tableAdapter?.notifyDataSetChanged()

    }
}
