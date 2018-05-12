package com.noraalosily.table

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class FileAdapter(private val files: FileModel) : RecyclerView.Adapter<FileAdapter.ViewHolder>() {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val nameView: TextView
        val dateView: TextView
        val timeView: TextView

        init {
            // Define click listener for the RowHolder's View.
            v.setOnClickListener{
                Log.d(TAG, "Element $adapterPosition clicked.")
            }
            nameView = v.findViewById(R.id.filenameTextView) as TextView
            dateView = v.findViewById(R.id.dateView) as TextView
            timeView = v.findViewById(R.id.timeView) as TextView
        }
    }


    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view.
        val v = LayoutInflater.from(viewGroup.context)
                .inflate(R.layout.file_item, viewGroup, false)

        Log.d(TAG, "onCreateViewHolder")
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        Log.d(TAG, "Element $position set.")

        // Get element from your dataset at this position and replace the contents of the view
        // with that element
        viewHolder.nameView.text = files.get(position)
    }
    override fun getItemCount(): Int {
        Log.d(TAG, "getItemCount")
        return files.size()
    }

    companion object {
        private const val TAG = "TableAdapter"
    }
}
