package com.noraalosily.table

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.View.OnFocusChangeListener
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.text.Editable
import android.text.TextWatcher



/*******************************
 *
 *
 *
 * TableAdapter
 *
 *
 *
 *******************************/
class TableAdapter(private val table: TableModel, tableRecyclerFragment: TableRecyclerFragment?) : RecyclerView.Adapter<RowHolder>(), RowHolder.RowHolderListener {


    //implements model modification RowHolder.RowHolderListener: deleteCell, editX, editY
    //interface for the recyclerview to access scrollToTop
    interface AdapterListener {
        fun scrollToTop(position: Int)
    }

    private var listener: AdapterListener? = null
    var currentX = 0.0
    var currentY = 0.0
    var xIsTheLastModified = false
    var currentPosition = 0

    init {
        listener = tableRecyclerFragment
    }


    // Adapter Listener calls
    override fun editX(position: Int, value:Double) {
        table.setX(position,value)
        table.print(position)
    }

    override fun editY(position: Int, value: Double) {

        table.setY(position,value)
        table.print(position)
       // listener?.scrollToTop(position)
    }

    override fun setPosition(position: Int){
        currentPosition = position
    }

    // Row Listener implementation
    override fun deleteCell(position: Int) {

        Log.e(TAG, "deleting $position")
        table.delete(position)
        table.print()
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
        rowHolder.xCell.clearFocus()
        rowHolder.yCell.clearFocus()
    }


    override fun getItemCount(): Int {
        return table.size()
    }

    fun changeTableName(name: String){
        table.setName(name)
    }

    override fun currentX(x: Double) {
        currentX = x
    }

    override fun currentY(y: Double) {
        currentY = y
    }

    override fun xIsTheLasModified(flag: Boolean) {
        xIsTheLastModified = flag
    }





    companion object {
        private const val TAG = "TableAdapter"
    }


}


/*******************************
*
*
*
* RowHolder
*
*
*
 *******************************/

class RowHolder(row: View, tableAdapter: TableAdapter) : RecyclerView.ViewHolder(row) {

    companion object {
        private const val TAG = "RowHolder"
    }



    interface RowHolderListener {
        fun deleteCell(position: Int)
        fun setPosition(position: Int)
        fun currentX(x: Double)
        fun currentY(y: Double)
        fun editX(position: Int, value: Double)
        fun editY(position: Int, value: Double)
        fun xIsTheLasModified(flag:Boolean)
    }


    val xCell: EditText = row.findViewById(R.id.xCell) as EditText
    val yCell: EditText = row.findViewById(R.id.yCell) as EditText
    val colorButton: Button = row.findViewById(R.id.colorButton) as Button
    private val deleteCell: ImageButton = row.findViewById(R.id.deleteButton) as ImageButton
    private val layout: LinearLayout = row.findViewById(R.id.rowLayout) as LinearLayout
    private var listener: RowHolderListener? = null

    init {
        listener = tableAdapter

        xCell.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val value = s.toString().toDouble()
                listener?.editX(adapterPosition,value)
            }

        })

        yCell.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val value = s.toString().toDouble()
                listener?.editY(adapterPosition,value)
                listener?.setPosition(adapterPosition)
            }

        })


        xCell.setOnTouchListener(View.OnTouchListener { v, _ ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            false
        })




        yCell.setOnTouchListener(View.OnTouchListener { v, _ ->
            v.isFocusable = true
            v.isFocusableInTouchMode = true
            false
        })

        colorButton.setOnClickListener{
            Log.e(TAG, "color $adapterPosition clear.")
            it.requestFocus()
        }

        deleteCell.setOnClickListener {
            xCell.clearFocus()
            xCell.isCursorVisible = false
            listener?.deleteCell(adapterPosition)
            listener?.setPosition(adapterPosition)


        }
    }
}