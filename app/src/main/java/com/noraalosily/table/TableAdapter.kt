package com.noraalosily.table

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView



class TableAdapter(private val table: TableModel) : RecyclerView.Adapter<TableAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val xCell: TextView
        val yCell: TextView

        init {
            // Define click listener for the ViewHolder's View.
            v.setOnClickListener{
                Log.d(TAG, "Element $adapterPosition clicked.")
            }
            xCell = v.findViewById(R.id.xCell) as TextView
            yCell = v.findViewById(R.id.yCell) as TextView

        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.table_item, viewGroup, false)

        Log.d(TAG, "onCreateViewHolder")
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.xCell.text = table.getX(position)
        viewHolder.yCell.text = table.getY(position)
    }
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return table.size()
    }

    companion object {
        private const val TAG = "TableAdapter"
    }
}
