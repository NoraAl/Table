package com.noraalosily.table

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log


class FileView : AppCompatActivity() , FileAdapter.FileAdapterInterface{
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var files: FileModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_recycler_view)

        files = intent.getSerializableExtra(InitialView.TAG) as FileModel

        viewManager = LinearLayoutManager(this)
        viewAdapter = FileAdapter(files, this)

        recyclerView = findViewById<RecyclerView>(R.id.filesRecyclerView).apply {
            // use this setting to improve performance if you know that changes
            // in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)

            // use a linear layout manager
            layoutManager = viewManager

            // specify an viewAdapter (see also next example)
            adapter = viewAdapter

        }

    }

    /*****************************************
    *
    * FileAdapterInterface implementation
    *
    *****************************************/
    override fun openFile(position: Int) {
        val openFileIntent = Intent()

        openFileIntent.putExtra(InitialView.FILE_RESULT, position)
        setResult(InitialView.FILE_CODE, openFileIntent)
        finish()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        Log.e("File", "dsj")
    }
}
