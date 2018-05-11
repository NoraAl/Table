package com.noraalosily.table

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout


class TableAdapter(private val table: TableModel, tableFragment: TableFragment?) : RecyclerView.Adapter<RowHolder>(), RowHolder.RowHolderListener {
    interface AdapterListener {
        fun scrollToTop(position: Int)
    }

    var listener: AdapterListener? = null

    init {
        listener = tableFragment
    }


    // Adapter Listener calls
    override fun editX(position: Int, value:Double) {
        table.setX(position,value)
        table.print()
    }

    override fun editY(position: Int) {
        listener?.scrollToTop(position)
    }

    // Row Listener implementation
    override fun deleteCell(position: Int) {
        Log.e(TAG, "deleting $position")
        table.delete(position)
        notifyItemRemoved(position)
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RowHolder {
        val row = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.table_item, viewGroup, false)
        return RowHolder(row, this)
    }

    override fun onBindViewHolder(rowHolder: RowHolder, position: Int) {
        rowHolder.xCell.setText( table.getX(position))
        rowHolder.yCell.setText( table.getY(position))
    }

    override fun getItemCount(): Int {
        Log.e(TAG, "getItemCount")
        return table.size()
    }


    companion object {
        private const val TAG = "TableAdapter"
    }


}


class RowHolder(row: View, tableAdapter: TableAdapter) : RecyclerView.ViewHolder(row) {

    companion object {
        private const val TAG = "RowHolder"
    }

    private var listener: RowHolderListener? = null

    interface RowHolderListener {
        fun deleteCell(position: Int)
        fun editX(position: Int, value: Double)
        fun editY(position: Int)
    }


    val xCell: EditText = row.findViewById(R.id.xCell) as EditText
    val yCell: EditText = row.findViewById(R.id.yCell) as EditText
    private val deleteCell: ImageButton = row.findViewById(R.id.deleteButton) as ImageButton
    private val layout: LinearLayout = row.findViewById(R.id.rowLayout) as LinearLayout
    private var currentPosition = 0

    init {
        listener = tableAdapter
        layout.setOnClickListener {
            //            it.setBackgroundColor(Color.parseColor("#c0c0c0"))
            currentPosition = adapterPosition
        }
        xCell.setOnClickListener {
            Log.e(TAG, "X--- $adapterPosition clicked.")
            //listener?.editX(adapterPosition)
        }

        yCell.setOnClickListener {
            listener?.editY(adapterPosition)
        }

        deleteCell.setOnClickListener {
            Log.e(TAG, "delete---- $adapterPosition clicked.")
            listener?.deleteCell(adapterPosition)

        }

        xCell.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (!hasFocus) {
                val x = (view as EditText).text.toString()
                listener?.editX(adapterPosition, x.toDouble())
            }
        }


    }
}