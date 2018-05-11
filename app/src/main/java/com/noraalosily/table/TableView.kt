package com.noraalosily.table

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.table_view.*

class TableView : AppCompatActivity() {

    companion object {
        private const val TAG = "TableView"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_view)

        var recyclerFragment = fragmentManager.findFragmentById(R.id.tablePortFragment)

        if (recyclerFragment == null) {
            recyclerFragment = TableRecyclerFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.tablePortFragment, recyclerFragment)
                    .commit()
        }
        newValueButton.setOnClickListener{
            Log.e(TAG,"newValueButton")

            val f = TableKeyboardFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.tablePortFragment, f)
            fragmentTransaction.commit()

        }
    }
}
