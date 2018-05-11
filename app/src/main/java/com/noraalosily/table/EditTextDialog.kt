package com.noraalosily.table

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.edit_text_dialog.*
import android.content.Intent
import android.widget.EditText


class EditTextDialog : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_text_dialog)

        val name = intent.getSerializableExtra(TableView.TAG) as String
        editTableName.setText(name)
        editTableName.setOnClickListener {
            (it as EditText).selectAll()
        }

        doneButton.setOnClickListener{
           val newname =  editTableName.text.toString()
            finish(newname)
        }

        cancelButton.setOnClickListener{
            finish()
        }
    }

    private fun finish(newname: String) {
        val nameIntent = Intent()
        nameIntent.putExtra(TableView.RESULT, newname)
        setResult(TableView.CODE, nameIntent)
        finish()

    }
}
