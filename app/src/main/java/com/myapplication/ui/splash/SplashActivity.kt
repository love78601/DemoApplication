package com.myapplication.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.myapplication.BuildConfig
import com.myapplication.R
import com.myapplication.ui.main.MainActivity
import com.myapplication.utils.AppConstant
import kotlinx.android.synthetic.main.activity_main.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init(){
        versionTv.text= BuildConfig.VERSION_NAME
        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))

            finish()
        }, AppConstant.SPLASH_DELAY)
    }
}