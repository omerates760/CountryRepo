package com.example.omera.oppdeneme

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val aa:Fragment=RegionFragment()
        val manager=this@MainActivity.supportFragmentManager
        val trans=manager.beginTransaction()
        trans.add(R.id.container,aa)
        trans.addToBackStack("FragRegion")
        trans.commit()
    }
}
