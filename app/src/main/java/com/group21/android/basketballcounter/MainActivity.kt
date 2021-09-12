package com.group21.android.basketballcounter

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fragment = GameListFragment.newInstance()


        val ft = supportFragmentManager.beginTransaction()
        Log.d("TEST", "BEGIN TRANSACTION SUCCEEDED")
        ft.replace(R.id.fragment_placeholder, fragment)
        Log.d("TEST", "FRAGMENT REPLACE SUCCEEDED")
        ft.commit()
        Log.d("TEST", "FRAGMENT COMMIT SUCCEEDED")
    }


    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart Called")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop Called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume Called")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause Called")
    }


    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy Called")
    }


}
