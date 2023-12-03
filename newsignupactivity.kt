package com.example.wwww.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wwww.R
import com.example.wwww.firebase.firestoreclass
import com.example.wwww.models.User
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class newsignupactivity : baseactivity() {
    var nam:EditText?=null
    var email:EditText?=null
    var pass:EditText?=null
    var signupbutton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_newsignupactivity)
        nam=findViewById(R.id.newsignupname)
        email=findViewById(R.id.newsignupemail)
        pass=findViewById(R.id.newsignuppassword)
        signupbutton=findViewById(R.id.newbuttonsignup)
        signupbutton?.setOnClickListener{
            signupuser()
        }

    }
    fun userregisteredsuccess(){
        Toast.makeText(this@newsignupactivity,"USER REGISTERED",Toast.LENGTH_LONG).show()
        FirebaseAuth.getInstance().signOut()
        finish()
    }
    private fun signupuser(){
        val name:String=nam?.text.toString().trim{ it <= ' '}
        val emailid:String=email?.text.toString().trim{ it <= ' '}
        val password:String=pass?.text.toString().trim(){ it <= ' '}
        if (validateform(name,emailid,password)){
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailid,password).addOnCompleteListener{
                    task ->
                if(task.isSuccessful){
                    val firebaseuser: FirebaseUser =task.result!!.user!!
                    val registerdemail=firebaseuser.email
                    val user=User(firebaseuser.uid,name,emailid)
                    firestoreclass().registeruser(this@newsignupactivity,user)
                    finish()
                }else{
                    Toast.makeText(this@newsignupactivity,task.exception!!.message,Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun validateform(a:String,b:String,c:String):Boolean{
        return when{
            TextUtils.isEmpty(a) ->{
                false
            }
            TextUtils.isEmpty(b) ->{
                false
            }
            TextUtils.isEmpty(c) ->{
                false
            }else ->{
                true
            }
        }
    }
}