package com.example.photoviewer.view

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.photoviewer.MainActivity
import com.example.photoviewer.R
import com.example.photoviewer.util.PrefConstant
import com.example.todonotes.onBoarding.OnBoardingActivity

class SplashActivity : AppCompatActivity() {
    private val TAG = "SplashActivity"
    lateinit var sharedPrefrences : SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        setupSharedPreferences()
        checkLoginStatus()
    }

    private fun checkLoginStatus() {
        val isBoardingSuccess = sharedPrefrences.getBoolean(PrefConstant.ON_BOARDED_SUCCESSFULLY, false)
        if(isBoardingSuccess){
            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
        } else {
                val intent = Intent(this@SplashActivity, OnBoardingActivity::class.java)
                startActivity(intent)
        }
        // to kill the instance of activity
        finish()
    }

    private fun setupSharedPreferences() {
        sharedPrefrences = getSharedPreferences(PrefConstant.SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE)
    }
}