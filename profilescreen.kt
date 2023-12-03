package com.example.wwww.activities

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.example.wwww.R
import com.example.wwww.firebase.firestoreclass
import com.example.wwww.models.User
import com.example.wwww.util.Constant

class profilescreen : baseactivity() {
    var a:Toolbar?=null
    var b:EditText?=null
    var c:EditText?=null
    var d:EditText?=null
    var buttonupdate:Button?=null
    private lateinit var muserdetails:User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profilescreen)
        a=findViewById(R.id.profiletoolbar)
        b=findViewById(R.id.profilename)
        c=findViewById(R.id.profileemail)
        d=findViewById(R.id.profilemobile)
        buttonupdate=findViewById(R.id.profileupdate)
        buttonupdate?.setOnClickListener{
            updateuserprofiledata()
        }
        setupactionbar()
        firestoreclass().loaduserdata(this@profilescreen)
    }
    private fun setupactionbar(){
        setSupportActionBar(a)
        val actionBar=supportActionBar
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true)
            actionBar.setHomeAsUpIndicator(R.drawable.whiteback)
            actionBar.title="Profile"
        }
        a?.setNavigationOnClickListener{onBackPressed()}
    }
    private fun updateuserprofiledata(){
        var z=false
        val userhashmap=HashMap<String,Any>()
        if(b?.text.toString()!=muserdetails.name){
            userhashmap[Constant.NAME]=b?.text.toString()
            z=true
        }
        if(d?.text.toString()!=muserdetails.mobile.toString()){
            userhashmap[Constant.MOBILE]=d?.text.toString().toLong()
            z=true
        }
        if(z){
            firestoreclass().updateuserprofiledata(this@profilescreen,userhashmap)
        }

    }
    fun setuserdatainui(user:User){
        muserdetails=user
        b?.setText(user.name)
        c?.setText(user.email)
        if(user.mobile!=0L){
            d?.setText(user.mobile.toString())
        }
    }

}