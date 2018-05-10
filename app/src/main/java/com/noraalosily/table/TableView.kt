package com.noraalosily.table

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class TableView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_view)

        var fragment = fragmentManager.findFragmentById(R.id.tablePortFragment)

        if (fragment == null) {
            fragment = TableFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.tablePortFragment, fragment)
                    .commit()
        }
    }
}
