package com.group21.android.basketballcounter

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.location.*
import java.util.*


private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), GameListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //val fragment = GameListFragment.newInstance()
        val ft = supportFragmentManager.beginTransaction()
        Log.d("TEST", "BEGIN TRANSACTION SUCCEEDED")
        ft.replace(R.id.fragment_placeholder, GameFragment())
        Log.d("TEST", "FRAGMENT REPLACE SUCCEEDED")
        ft.commit()
        Log.d("TEST", "FRAGMENT COMMIT SUCCEEDED")

        /*val ft = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_placeholder, fragment)
        ft.commit()*/
    }

    override fun onGameSelected(gameId: UUID) {
        Log.d(TAG, "MainActivity.onGameSelected: $gameId")
        val fragment = GameFragment.newInstance(gameId)
        supportFragmentManager.beginTransaction().replace(R.id.fragment_placeholder, fragment).addToBackStack(null).commit()
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
