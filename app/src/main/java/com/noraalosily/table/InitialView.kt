package com.noraalosily.table

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.initial_view.*

class InitialView : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.initial_view)

        newButton.setOnClickListener {
            val intent = Intent(this, FileView::class.java)
            this.startActivity(intent)
        }
    }
}
