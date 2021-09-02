package com.group21.android.basketballcounter

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun threeTeam1(view: View) {}
    fun twoTeam1(view: View) {}
    fun oneTeam1(view: View) {}
    fun threeTeam2(view: View) {}
    fun twoTeam2(view: View) {}
    fun oneTeam2(view: View) {}
    fun resetScore(view: View) {}
}
