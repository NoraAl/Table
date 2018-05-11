package com.noraalosily.table

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.app.Fragment
import android.graphics.Color
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout


class TableKeyboardFragment : Fragment() {

    companion object {
        private const val TAG = "TableKeyboardFragment"
    }

    private var xView: EditText? = null
    private var yView: EditText? = null
    private var layoutView: LinearLayout? = null

    private var x :Double? = null
    var y = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        x = 0.0

        Log.e(TAG,"********")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val fragmentView =  inflater.inflate(R.layout.table_keyboard_fragment, container, false)
        xView = fragmentView.findViewById(R.id.xEdit)
        yView = fragmentView.findViewById(R.id.yEdit)
        layoutView = fragmentView.findViewById(R.id.editableRow)


        Log.e(TAG,"---")
        layoutView?.setBackgroundColor(Color.parseColor("#8A6B778A"))
        return fragmentView
    }


}
