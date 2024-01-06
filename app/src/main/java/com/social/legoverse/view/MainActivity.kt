package com.social.legoverse.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.ozgurbaykal.hostmobile.view.HomeFragment
import com.social.legoverse.R
import com.social.legoverse.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val TAG = "_MainActivity"

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav : BottomNavigationView

    private var mailOrUserName: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)

        changeFragment(HomeFragment(), R.id.mainActivityContainer,"HomeFragmentTAG")


        bottomNav = binding.bottomNavigation

        bottomNav.setOnItemSelectedListener  {item ->
            when (item.itemId) {
                R.id.navigation_home -> {
                    Log.i(TAG, "Bottom Nav ClickEvent -> PAGE 1")
                    changeFragment(HomeFragment(), R.id.mainActivityContainer, "CustomFragmentTag")
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_search -> {
                    Log.i(TAG, "Bottom Nav ClickEvent -> PAGE 2")
                    return@setOnItemSelectedListener true
                }
                R.id.navigation_add -> {
                    Log.i(TAG, "Bottom Nav ClickEvent -> PAGE 3")

                    mailOrUserName = intent.getStringExtra("user_name_or_mail").toString()

                    val intent = Intent (this@MainActivity, AddPostActivity::class.java)
                    startActivity(intent)

                    return@setOnItemSelectedListener true
                }
                R.id.navigation_notifications -> {
                    Log.i(TAG, "Bottom Nav ClickEvent -> PAGE 4")

                    return@setOnItemSelectedListener true
                }
            }
            false
        }

    }

    private fun changeFragment(fragment: Fragment, frameId: Int, tag: String) {
        supportFragmentManager.beginTransaction().apply {
            replace(frameId, fragment, tag)
            commit()
        }
    }

    override fun onResume() {
        super.onResume()
        bottomNav.selectedItemId = R.id.navigation_home;
        changeFragment(HomeFragment(), R.id.mainActivityContainer,"HomeFragmentTAG")

    }
}