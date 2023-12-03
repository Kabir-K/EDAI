package com.example.wwww.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.wwww.R
import com.example.wwww.models.User
import com.google.firebase.auth.FirebaseAuth

class signin : baseactivity() {
    private lateinit var auth: FirebaseAuth
    var buttonsignin:Button?=null
    var signinemail:EditText?=null
    var signinpass:EditText?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        auth=FirebaseAuth.getInstance()
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signin)
        signinemail=findViewById(R.id.signinemailid)
        signinpass=findViewById(R.id.signinpassword)
        buttonsignin=findViewById(R.id.newbuttonsignin)
        buttonsignin?.setOnClickListener{
            signinregistereduser()
        }
    }
    fun signinsuccess(user:User){
        startActivity(Intent(this@signin,MainActivity::class.java))
        finish()
    }
    private fun signinregistereduser(){
        val emailid:String=signinemail?.text.toString().trim{ it <= ' '}
        val password:String=signinpass?.text.toString().trim(){ it <= ' '}
        if(validateform(emailid,password)){
            auth.signInWithEmailAndPassword(emailid,password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Sign in success, update UI with the signed-in user's information
                        Log.d("SIGN IN", "createUserWithEmail:success")
                        val user = auth.currentUser
                        startActivity(Intent(this@signin,MainActivity::class.java))
                    } else {
                        Toast.makeText(this@signin,task.exception!!.message, Toast.LENGTH_LONG).show()
                    }
                }
        }
    }
    private fun validateform(a:String,b:String):Boolean{
        return when{
            TextUtils.isEmpty(a) ->{
                false
            }
            TextUtils.isEmpty(b) ->{
                false
            }else ->{
                true
            }
        }
    }
}