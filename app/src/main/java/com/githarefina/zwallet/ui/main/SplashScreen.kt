package com.githarefina.zwallet.ui.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.githarefina.zwallet.R
import android.os.Handler
import com.githarefina.zwallet.ui.auth.AuthActivity
import com.githarefina.zwallet.utils.KEY_LOGGED_IN
import com.githarefina.zwallet.utils.PREFS_NAME
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var prefs :SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        token()
    }
    fun token(){
        prefs = getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE)
        if(prefs.getBoolean(KEY_LOGGED_IN,false)){
            splashLogin()
        }else{
            splashLogin()
        }

    }
    fun splashLogin(){
        Handler().postDelayed({
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }

    fun splashMain(){
        Handler().postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        },2000)
    }
    override fun onBackPressed() {
        super.onBackPressed()
    }

}