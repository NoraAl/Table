package com.noraalosily.table

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FileAdapter(private val files: FileModel, fileView: FileView?) : RecyclerView.Adapter<ViewHolder>(), ViewHolder.ViewHolderInterface {
    interface FileAdapterInterface {
        fun openFile(position: Int)
    }

    private var listener: FileAdapterInterface? = null
    init {
        listener = fileView
    }

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.file_item, viewGroup, false)
        return ViewHolder(v, this)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.nameView.text = files.getFilename(position)
        viewHolder.dateView.text = files.getDate(position)
        viewHolder.timeView.text = files.getTime(position)
    }

    override fun getItemCount(): Int = files.size()

    /*****************************************
     *
     * ViewHolderInterface implementation
     *
     *****************************************/
    override fun openFile(position: Int) {
        listener?.openFile(position)
    }

    companion object {
        private const val TAG = "TableAdapter"
    }
}




/*******************************
 *
 *
 *
 * ViwHolder
 *
 *
 *
 *******************************/

class ViewHolder(v: View, adapter: FileAdapter) : RecyclerView.ViewHolder(v) {
    interface ViewHolderInterface {
        fun openFile(position: Int)
    }
    private var listener: ViewHolderInterface? = null
    val nameView: TextView
    val dateView: TextView
    val timeView: TextView

    init {
        listener = adapter

        v.setOnClickListener {
            listener?.openFile(adapterPosition)
        }
        nameView = v.findViewById(R.id.filenameTextView) as TextView
        dateView = v.findViewById(R.id.dateView) as TextView
        timeView = v.findViewById(R.id.timeView) as TextView
    }
}
