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
import android.view.ViewTreeObserver




class TableFragment : Fragment(), TableAdapter.AdapterListener {
    companion object {
        private const val TAG = "TableFragment"
    }


    private var recyclerView: RecyclerView? = null
    private lateinit var  viewManager:LinearLayoutManager
    private lateinit var tableAdapter: RecyclerView.Adapter<*>
    private lateinit var tables: TableModel
    private var topPosition = 0

    override fun scrollToTop(position: Int) {
        val firstPosition = viewManager.findFirstVisibleItemPosition()
        val firstItemView = viewManager.findViewByPosition(firstPosition)
        val offset = firstItemView.top

        viewManager?.scrollToPositionWithOffset(position, offset)
        tableAdapter.notifyDataSetChanged()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewManager = LinearLayoutManager(context)
        tables = TableModel()
        tableAdapter = TableAdapter(tables, this)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val fragmentView =  inflater?.inflate(R.layout.table_recycler_view, container, false)
        // get the <android.support.v7.widget.RecyclerView..>
        // from the inflated view (fragmentView) and bind it to recyclerView
        recyclerView = fragmentView?.findViewById(R.id.tableRecyclerView)
        recyclerView?.setHasFixedSize(true)
        recyclerView?.layoutManager = viewManager
        recyclerView?.adapter = tableAdapter

        recyclerView?.viewTreeObserver?.addOnGlobalLayoutListener {
            topPosition = viewManager.findFirstVisibleItemPosition()
        }



        return fragmentView
    }
}
