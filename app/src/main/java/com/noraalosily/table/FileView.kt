package com.noraalosily.table

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView


class FileView : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var files: FileModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.file_recycler_view)

        //1. add libraries: recycler and card
        //2. create model, recyclerview and its class, item adn its adapter and holder
        //3. here, in the recylerivew class, create model and Linear layout manager
        files = FileModel()

        viewManager = LinearLayoutManager(this)
        viewAdapter = FileAdapter(files)

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
}
