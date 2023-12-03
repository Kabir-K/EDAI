package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.wwww.R

class intro : baseactivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        var signupbutton: Button =findViewById(R.id.signupbutton)
        signupbutton.setOnClickListener{
            startActivity(Intent(this@intro, newsignupactivity::class.java))
        }
        var signinbutton: Button =findViewById(R.id.signinbutton)
        signinbutton.setOnClickListener{
            startActivity(Intent(this@intro, signin::class.java))
        }
    }
}