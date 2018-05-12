package com.noraalosily.table

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.initial_view.*
import kotlinx.android.synthetic.main.table_view.*
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class InitialView : AppCompatActivity() {
    companion object {
        const val TAG = "InitialView_TAG"
        const val TABLE_FILES_NAME = "TABLE_FILES_NAME"
        const val RESULT = "InitialView_RESULT"
        const val CODE = 55
    }

    //lateinit var context: Context
    private lateinit var files : FileModel
    private lateinit var ctx:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_view)
        ctx = applicationContext
        files = FileModel()


        newButton.setOnClickListener {
            val intent = Intent(this, TableView::class.java)

            startActivityForResult(intent,CODE)
        }

        openButton.setOnClickListener {
            files = loadFiles()
            if (files.isEmpty()){
                val intent = Intent(this, TableView::class.java)
                startActivityForResult(intent,CODE)
            } else {
                val intent = Intent(this, FileView::class.java)
                intent.putExtra(TAG,files)
                this.startActivity(intent)
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.e(TAG, "receiving")
        if (requestCode == CODE) {
            val meta = data?.getSerializableExtra(RESULT) as FileMeta
            files.addFile(meta)

            // save file

            val fileOutputStream = openFileOutput(TABLE_FILES_NAME, Context.MODE_PRIVATE)
            ObjectOutputStream(fileOutputStream).apply {

                writeObject(files)
                close()
            }

            fileOutputStream.close()
            Log.e("sssss","shahdka")

        }
    }

    private fun loadFiles():FileModel{
        var obj: FileModel? = null
        val file = File(ctx.filesDir, TABLE_FILES_NAME)
        if (!file.exists())
            return FileModel()

        openFileInput(InitialView.TABLE_FILES_NAME).use {
            ObjectInputStream(it).apply {
                obj = readObject() as FileModel
                close()
            }
            it.close()
        }
        return obj?: FileModel()
    }

}
