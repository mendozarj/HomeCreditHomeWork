package com.example.homecredittest.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.homecredittest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    var pragueFav: Boolean = false
    var manilaFav: Boolean = false
    var seoulFav: Boolean  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))


        //Test Comment
    }
}