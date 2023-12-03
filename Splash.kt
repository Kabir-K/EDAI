package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.wwww.R
import com.example.wwww.firebase.firestoreclass

class splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        Handler().postDelayed({
            var currentuserid=firestoreclass().getcurrentuserid()
            if(currentuserid.isNotEmpty()){
                startActivity(Intent(this@splash,MainActivity::class.java))
                finish()
            }else{
                startActivity(Intent(this@splash, intro::class.java))
                finish()
            }
        },2500)
    }
}