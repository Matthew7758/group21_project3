package com.group21.android.basketballcounter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val pogBtn = findViewById<Button>(R.id.pogBtn)
        pogBtn.setOnClickListener {
            pogBtn.isEnabled = false
        }
    }

    override fun onBackPressed() {
        Log.d("TEST", "BACKBUTTON PRESSED")

        val pogBtn = findViewById<Button>(R.id.pogBtn)
        if(!pogBtn.isEnabled) {
            Log.d("TEST", "POGBTN DISABLED")
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Vinny Pog!")
            setResult(RESULT_OK, returnIntent)
            Log.d("TEST", returnIntent.toString())
            super.onBackPressed()
            finish()
        }
        else {
            Log.d("TEST", "POGBTN ENABLED")
            val returnIntent = Intent()
            setResult(RESULT_CANCELED, returnIntent)
            Log.d("TEST", returnIntent.toString())
            super.onBackPressed()
            finish()
        }
    }
}
