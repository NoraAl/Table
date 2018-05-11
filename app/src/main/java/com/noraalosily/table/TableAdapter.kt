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
    }

    override fun getItemCount(): Int {
        return table.size()
    }

    fun changeTableName(name: String){
        table.setName(name)
    }

//    fun clearFocus(){
//        this.p
//    }


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

    private var listener: RowHolderListener? = null
    private var imm: InputMethodManager

    interface RowHolderListener {
        fun deleteCell(position: Int)
        fun editX(position: Int, value: Double)
        fun editY(position: Int, value: Double)
    }


    val xCell: EditText = row.findViewById(R.id.xCell) as EditText
    val yCell: EditText = row.findViewById(R.id.yCell) as EditText
    val colorButton: Button = row.findViewById(R.id.colorButton) as Button
    private val deleteCell: ImageButton = row.findViewById(R.id.deleteButton) as ImageButton
    private val layout: LinearLayout = row.findViewById(R.id.rowLayout) as LinearLayout
    private var currentPosition = 0

    init {
        listener = tableAdapter
        imm  = layout.context.getSystemService(Context.INPUT_METHOD_SERVICE)as InputMethodManager

        xCell.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (adapterPosition<0) return@OnFocusChangeListener// called after deleting this position:out of boundary error

            if (!hasFocus) {
                val x = (view as EditText).text.toString()
                Log.e(TAG, "x $adapterPosition should be saved.")
                listener?.editX(adapterPosition, x.toDouble())
            }
        }

        yCell.onFocusChangeListener = OnFocusChangeListener { view, hasFocus ->
            if (adapterPosition<0) return@OnFocusChangeListener // called after deleting this position:out of boundary error

            if (!hasFocus) {
                val y = (view as EditText).text.toString()
                Log.e(TAG, "x $adapterPosition should be saved.")
                listener?.editY(adapterPosition, y.toDouble())
            }
        }

        colorButton.setOnClickListener{
            Log.e(TAG, "color $adapterPosition clear.")
            it.requestFocus()
        }

        deleteCell.setOnClickListener {
            Log.e(TAG, "delete---- $adapterPosition clicked.")
//            yCell.clearFocus()
//            yCell.isCursorVisible = false

            xCell.clearFocus()
            xCell.isCursorVisible = false

            listener?.deleteCell(adapterPosition)

        }

        layout.setOnClickListener{
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            yCell.clearFocus()
            yCell.isCursorVisible = false
            xCell.clearFocus()
            xCell.isCursorVisible = false
            it.requestFocus()
        }

    }

    fun click(){
        layout.callOnClick()
    }
}