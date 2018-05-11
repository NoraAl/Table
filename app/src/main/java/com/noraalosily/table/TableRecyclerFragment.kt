package com.noraalosily.table


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


class TableRecyclerFragment : Fragment(), TableAdapter.AdapterListener {
    companion object {
        private const val TAG = "TableRecyclerFragment"
    }

    private var recyclerView: RecyclerView? = null
    private var tableAdapter: RecyclerView.Adapter<*>
    private var table = TableModel()
    private lateinit var  viewManager: LinearLayoutManager

    init {
        tableAdapter = TableAdapter(table, this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewManager = LinearLayoutManager(context)
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

    fun newValue(){
        val adapter = (tableAdapter as TableAdapter)

//        //first get current from focused EditText
//        if (adapter.xIsTheLastModified)
//            table.setX(adapter.currentPosition, adapter.currentX )
//        else
//            table.setY(adapter.currentPosition, adapter.currentY)


        val position = table.add()

        adapter.notifyDataSetChanged()
        table.print()

        viewManager.scrollToPosition(position)

    }

    override fun scrollToTop(position: Int) {
        val firstPosition = viewManager.findFirstVisibleItemPosition()
        val firstItemView = viewManager.findViewByPosition(firstPosition)
        val offset = firstItemView.top

        viewManager.scrollToPositionWithOffset(position, offset)
        tableAdapter.notifyDataSetChanged()

    }
}
