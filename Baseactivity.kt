package com.example.wwww.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.wwww.R
import com.google.firebase.auth.FirebaseAuth

open class baseactivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baseactivity)
    }
    fun getCurrentUserId():String{
        return FirebaseAuth.getInstance().currentUser!!.uid
    }
}