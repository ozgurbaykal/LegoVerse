package com.social.legoverse.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import com.social.legoverse.R
import com.social.legoverse.manager.SharedPreferenceManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        SharedPreferenceManager.init(this@MainActivity)

       // if(SharedPreferenceManager.readBoolean("isNewUser", true) == true){
            val intent = Intent (this@MainActivity, TutorialActivity::class.java)
            startActivity(intent)
        //}
    }

    override fun onResume() {
        super.onResume()

        this.window?.statusBarColor = ContextCompat.getColor(this@MainActivity, R.color.white)

    }
}