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

        val rf = recyclerFragment as TableRecyclerFragment//pointer
        tableNameView.text = rf.getTableName()
        newValueButton.setOnClickListener{
            rf.newValue()
        }

        tableNameView.setOnClickListener{
            val dialogIntent = Intent(this, EditTextDialog::class.java)
            val name = rf.getTableName()
            dialogIntent.putExtra(TAG,name)
            startActivityForResult(dialogIntent,CODE)
        }

        homeButton.setOnClickListener{
            returnToHome()
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

    override fun onBackPressed() {
        super.onBackPressed()
        returnToHome()
    }

    private fun returnToHome(){
        finish()
        Log.e(TAG,"finishing")
    }
}
