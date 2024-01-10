package com.social.legoverse.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.social.legoverse.R
import com.social.legoverse.databinding.ActivityMainBinding
import com.social.legoverse.databinding.ActivityProfileBinding
import com.social.legoverse.util.AppDatabase
import com.social.legoverse.util.UserNameOrMail
import com.social.legoverse.util.Users
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var recyclerView: RecyclerView

    private lateinit var imageViewProfile: ImageView
    private lateinit var userName: TextView
    private lateinit var realName: TextView
    private lateinit var birthDay: TextView
    private lateinit var user: Users
    private lateinit var topBar : Toolbar

    private var isFromMain: Boolean= false
    private lateinit var userNameOrMailChoose: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityProfileBinding.inflate(layoutInflater)

        val view = binding.root
        setContentView(view)
        recyclerView = binding.recyclerProfilePost
        imageViewProfile = binding.imageViewProfile
        userName = binding.userName
        realName = binding.realName
        birthDay = binding.birthDay
        topBar = binding.toolbar

        setSupportActionBar(topBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        topBar.setNavigationOnClickListener {
            finish()
        }

        recyclerView.layoutManager = LinearLayoutManager(this@ProfileActivity)

        val postDao = AppDatabase.getDatabase(this@ProfileActivity).postDao()
        val userDao = AppDatabase.getDatabase(this@ProfileActivity).userDao()

        CoroutineScope(Dispatchers.IO).launch {

            isFromMain = intent.getBooleanExtra("is_from_main", false)

            Log.i("ProfileActivity", "isFromMain: $isFromMain")

            if(isFromMain){
                if (intent.getIntExtra("user_id", -1) != -1)
                    user = userDao.findUserWithId(intent.getIntExtra("user_id", -1))!!
            }else{
                user = userDao.findUserByUsernameOrEmail(UserNameOrMail.userNameOrMail)!!
            }


            if (user != null) {
                val posts = postDao.getPostsListById(user.id)

                val postAdapter = ProfilePostAdapter(user, posts, this@ProfileActivity)
                recyclerView.adapter = postAdapter

                val bitmapProfile = user.profileImage?.let { byteArrayToBitmap(it) }
                imageViewProfile.setImageBitmap(bitmapProfile)

                userName.text = user.userName
                realName.text = user.name

                val dateFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())

                birthDay.text = dateFormat.format(user.birthday)
            }
        }
    }

    fun byteArrayToBitmap(byteArray: ByteArray): Bitmap {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }
}