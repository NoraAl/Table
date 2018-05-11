package com.noraalosily.table

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.*
import android.view.Window
import android.widget.EditText
import kotlinx.android.synthetic.main.table_view.*
import android.R.attr.data




class TableView : AppCompatActivity() {

    companion object {
        const val TAG = "TableView"
        const val RESULT = "RESULT"
        const val CODE = 0
    }
    private var recyclerFragment: Fragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_view)
        supportActionBar?.hide()// hide the title bar

        recyclerFragment = fragmentManager.findFragmentById(R.id.tablePortFragment)
        if (recyclerFragment == null) {
            recyclerFragment = TableRecyclerFragment()
            fragmentManager.beginTransaction()
                    .add(R.id.tablePortFragment, recyclerFragment)
                    .commit()
        }

        tableNameView.text = (recyclerFragment as TableRecyclerFragment).getTableName()

        newValueButton.setOnClickListener{
            Log.e(TAG,"newValueButton")

            val f = TableKeyboardFragment()
            val fragmentManager = fragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.tablePortFragment, f)
            fragmentTransaction.commit()

        }

        tableNameView.setOnClickListener{
            val dialogIntent = Intent(this, EditTextDialog::class.java)
            val name = (recyclerFragment as TableRecyclerFragment).getTableName()
            dialogIntent.putExtra(TAG,name)
            startActivityForResult(dialogIntent,CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CODE) {
            val newname = data?.getStringExtra(RESULT)?: ""
            // change in both model and view
            (recyclerFragment as TableRecyclerFragment).changeTableName(newname)
            tableNameView.text = (recyclerFragment as TableRecyclerFragment).getTableName()// in case name is empty

        }
    }
}
