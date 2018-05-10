package com.noraalosily.table

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ImageButton
import android.widget.TextView



class TableAdapter(private val table: TableModel) : RecyclerView.Adapter<RowHolder>(), RowHolder.TableListener {
    override fun deleteCell(position: Int) {
        Log.e(TAG, "deleting $position")
    }




    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RowHolder {
        val row = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.table_item, viewGroup, false)
        return RowHolder(row,this)
    }

    override fun onBindViewHolder(rowHolder: RowHolder, position: Int) {
        rowHolder.xCell.text = table.getX(position)
        rowHolder.yCell.text = table.getY(position)
    }

    override fun getItemCount(): Int {
        Log.e(TAG, "getItemCount")
        return table.size()
    }

    companion object {
         private const val TAG = "TableAdapter"
    }


}


class RowHolder(row: View, ad:TableAdapter) : RecyclerView.ViewHolder(row) {
    var listener: TableListener? = null
    interface TableListener {
        fun deleteCell(position: Int)
    }

    companion object {
        private const val TAG = "RowHolder"
    }

    val xCell: TextView = row.findViewById(R.id.xCell) as TextView
    val yCell: TextView = row.findViewById(R.id.yCell) as TextView
    private val deleteCell: ImageButton = row.findViewById(R.id.deleteButton) as ImageButton

    init {
        listener = ad
        xCell.setOnClickListener{
            Log.e(TAG, "X--- $adapterPosition clicked.")
        }

        yCell.setOnClickListener{
            Log.e(TAG, "Y--- $adapterPosition clicked.")
        }

        deleteCell.setOnClickListener{
            Log.e(TAG, "delete---- $adapterPosition clicked.")
            listener?.deleteCell(adapterPosition)

        }

        row.setOnClickListener{
            Log.e(TAG, "Element---- $adapterPosition clicked.")
        }

    }
}