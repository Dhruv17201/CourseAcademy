package com.example.courseacademy


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.courseacademy.fragment.MainPageFragment
import com.example.courseacademy.fragment.MyCourseFragment
import com.example.courseacademy.fragment.MyProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.smarteist.autoimageslider.SliderView

class MainPage : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_page)
        var Homepagepage = MainPageFragment()
        var mycourse = MyCourseFragment()
        var myprofile = MyProfileFragment()

        makeCurrentFragment(Homepagepage)
        var btmnavmenu:BottomNavigationView = findViewById(R.id.bottomnav)
        btmnavmenu.setOnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.home -> makeCurrentFragment(Homepagepage)
                R.id.mycourses -> makeCurrentFragment(mycourse)
                R.id.myprofile -> makeCurrentFragment(myprofile)
            }
            true
        }

    }

    private fun makeCurrentFragment(fragment: Fragment) = supportFragmentManager.beginTransaction().apply {
        replace(R.id.Myframelayout,fragment)
        commit()
    }
}