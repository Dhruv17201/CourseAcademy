package com.example.courseacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var centerimg: ImageView =findViewById(R.id.centerlogo)
        var bottomlogo:ImageView= findViewById(R.id.bottomlogo)
        var cimage=AnimationUtils.loadAnimation(applicationContext,R.anim.centerimagerotation)
        centerimg.startAnimation(cimage)
        var bimg=AnimationUtils.loadAnimation(applicationContext,R.anim.bottomimagefadeinout)
        bottomlogo.startAnimation(bimg)
        Handler().postDelayed({
            var movetologin:Intent= Intent(applicationContext,LoginPage::class.java)
            startActivity(movetologin)
        },2700)
    }
}