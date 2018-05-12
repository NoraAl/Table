package com.noraalosily.table

import android.app.Fragment
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.table_view.*
import android.content.Context
import java.io.ObjectOutputStream


class TableView : AppCompatActivity() {
    companion object {
        const val TAG = "TableView"
        const val DIALOG_RESULT = "TABLE_RESULT"
        const val TABLE_ID = "TABLE_ID"//used to open a file
        const val DIALOG_CODE = 0
    }
    private var recyclerFragment: Fragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.table_view)
        supportActionBar?.hide()// hide the title bar

        val fileId = intent.getSerializableExtra(InitialView.TABLE_ID_I) as String

        recyclerFragment = fragmentManager.findFragmentById(R.id.tablePortFragment)
        if (recyclerFragment == null) {
            recyclerFragment = TableRecyclerFragment()
            val bundle = Bundle()
            bundle.putString(TABLE_ID, fileId)

            (recyclerFragment as TableRecyclerFragment).arguments = bundle

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
            startActivityForResult(dialogIntent,DIALOG_CODE)
        }

        homeButton.setOnClickListener{
            returnToHome()
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == DIALOG_CODE) {
            val newname = data?.getStringExtra(DIALOG_RESULT)?: ""
            // change in both model and view
            (recyclerFragment as TableRecyclerFragment).changeTableName(newname)
            tableNameView.text = (recyclerFragment as TableRecyclerFragment).getTableName()// in case name is empty

        }
    }

    override fun onBackPressed() {
        returnToHome()
    }

    private fun returnToHome(){
        val metaIntent = Intent()
        val table = (recyclerFragment as TableRecyclerFragment).getTable()

        // save file
//        val path = getFileStreamPath(table.getId())
//        Log.e(TAG,"in table view $path")
        val fileOutputStream = openFileOutput(table.getId(), Context.MODE_PRIVATE)
        ObjectOutputStream(fileOutputStream).apply {
            writeObject(table)
            close()
        }

        fileOutputStream.close()
        Log.e(TAG, "file saved")

        // send meta for files
        metaIntent.putExtra(InitialView.TABLE_RESULT, table.getMeta())
        setResult(InitialView.TABLE_CODE, metaIntent)
        finish()
        //Log.e(TAG,"finishing")
    }
}
