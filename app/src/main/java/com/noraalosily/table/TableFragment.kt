package com.noraalosily.table


import android.app.Fragment
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View


class TableFragment : Fragment() {
    private var recyclerView: RecyclerView? = null
    private lateinit var  viewManager:LinearLayoutManager
    private lateinit var tableAdapter: RecyclerView.Adapter<*>
    private lateinit var tables: TableModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        tables = TableModel()
        tableAdapter = TableAdapter(tables)
        viewManager = LinearLayoutManager(context)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val fragmentView =  inflater?.inflate(R.layout.table_recycler_view, container, false)
        // get the <android.support.v7.widget.RecyclerView..>
        // from the inflated view (fragmentView) and bind it to recyclerView
        recyclerView = fragmentView?.findViewById(R.id.tableRecyclerView)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = viewManager
        recyclerView?.adapter = tableAdapter

        return fragmentView
    }
}
