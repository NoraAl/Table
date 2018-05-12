package com.noraalosily.table

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.initial_view.*
import java.io.File
import java.io.ObjectInputStream
import java.io.ObjectOutputStream

class InitialView : AppCompatActivity() {
    companion object {
        const val TAG = "InitialView_TAG"
        const val TABLE_FILES_NAME = "TABLE_FILES_NAME"
        const val TABLE_ID_I = "TABLE_ID"
        const val TABLE_RESULT = "TABLE_RESULT"
        const val FILE_RESULT ="FILE_RESULT"
        const val TABLE_CODE = 0
        const val FILE_CODE = 1
    }

    private lateinit var files : FileModel
    private lateinit var ctx:Context

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_view)
        ctx = applicationContext

        clearFiles()

        newButton.setOnClickListener {
            files = loadFiles()
            newTableActivity("")//empty file
        }

        openButton.setOnClickListener {
            files = loadFiles()
            if (files.isEmpty()){
                val handler = Handler()
                handler.postDelayed({
                    newTableActivity("")
                }, 1000)
                Toast.makeText(applicationContext, "No files exist, opening new one",
                        Toast.LENGTH_SHORT).show();


            } else {
                val intent = Intent(this, FileView::class.java)
                intent.putExtra(TAG,files)
                startActivityForResult(intent, FILE_CODE)
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == TABLE_CODE) {
            val meta = data?.getSerializableExtra(TABLE_RESULT) as FileMeta
            //check if files exist
            if (!files.idExist(meta.id))
                files.addFile(meta)
            else{// file exists, update it in case of meta change
                val i = files.getPosition(meta.id)
                files.resetFile(i,meta)
            }

            //save file metas
            val fileOutputStream = openFileOutput(TABLE_FILES_NAME, Context.MODE_PRIVATE)
            ObjectOutputStream(fileOutputStream).apply {

                writeObject(files)
                close()
            }

            fileOutputStream.close()
            return
        }
        if (requestCode == FILE_CODE){
            val position = data?.getIntExtra(FILE_RESULT,-1)?: -1
            if (position<0)
                return
            newTableActivity(files.getId(position))
        }
    }

    fun newTableActivity(filename: String, noFiles:Boolean = false){
        val intent = Intent(this, TableView::class.java)
        intent.putExtra(TABLE_ID_I,filename)
        startActivityForResult(intent,TABLE_CODE)
    }

    private fun clearFiles(){
        val file = File(ctx.filesDir, TABLE_FILES_NAME)
        if (!file.exists())
            return
        file.delete()
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
