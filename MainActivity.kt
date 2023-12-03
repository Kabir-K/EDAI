package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.wwww.R
import com.example.wwww.firebase.firestoreclass
import com.example.wwww.models.User
import com.google.android.material.navigation.NavigationView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {
    var a:Toolbar?=null
    var b:DrawerLayout?=null
    var c:NavigationView?=null
    var d:TextView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        a=findViewById(R.id.toolbar_main)
        b=findViewById(R.id.drawerlayout)
        c=findViewById(R.id.navview)
        d=findViewById(R.id.usernameid)
        setupactionbar()
        c?.setNavigationItemSelectedListener(this@MainActivity)
        firestoreclass().loaduserdata(this@MainActivity)
    }
    private fun setupactionbar(){
        setSupportActionBar(a)
        a?.setNavigationIcon(R.drawable.navmenuicon)
        a?.setNavigationOnClickListener{
            toggledrawer()
        }
    }
    fun updatenavigationuserdetail(user:User){
        d?.text=user.name
    }
    private fun toggledrawer(){
        if(b?.isDrawerOpen(GravityCompat.START)==true){
            b?.closeDrawer(GravityCompat.START)
        }else{
            b?.openDrawer(GravityCompat.START)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.navmyprofile ->{
                startActivity(Intent(this@MainActivity,profilescreen::class.java))
            }
            R.id.navsignout ->{
                FirebaseAuth.getInstance().signOut()
                val intent= Intent(this@MainActivity,intro::class.java)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
                finish()
            }
        }
        b?.closeDrawer(GravityCompat.START)
        return true

    }

}