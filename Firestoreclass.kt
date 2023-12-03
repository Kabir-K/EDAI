package com.example.wwww.firebase

import android.app.Activity
import android.widget.Toast
import com.example.wwww.activities.MainActivity
import com.example.wwww.models.User
import com.example.wwww.activities.newsignupactivity
import com.example.wwww.activities.profilescreen
import com.example.wwww.activities.signin
import com.example.wwww.util.Constant
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions

class firestoreclass {
    private val mfirestore=FirebaseFirestore.getInstance()
    fun registeruser(activity:newsignupactivity,userinfo: User){
        mfirestore.collection(Constant.USERS)
            .document(getcurrentuserid()).set(userinfo, SetOptions.merge())
            .addOnSuccessListener{
                activity.userregisteredsuccess()
            }
    }
    fun loaduserdata(activity: Activity){
        mfirestore.collection(Constant.USERS)
            .document(getcurrentuserid())
            .get().addOnSuccessListener { document ->
                val loggedinuser=document.toObject(User::class.java)!!
                when(activity){
                    is signin ->{
                        activity.signinsuccess(loggedinuser)
                    }
                    is MainActivity ->{
                        activity.updatenavigationuserdetail(loggedinuser)
                    }
                    is profilescreen ->{
                        activity.setuserdatainui(loggedinuser)
                    }
                }
            }
    }
    fun updateuserprofiledata(activity:profilescreen,userhashmap:HashMap<String,Any>){
        mfirestore.collection(Constant.USERS)
            .document(getcurrentuserid())
            .update(userhashmap)
            .addOnSuccessListener {
                Toast.makeText(activity,"Profile Updated",Toast.LENGTH_LONG).show()
            }.addOnFailureListener{
                Toast.makeText(activity,"NO",Toast.LENGTH_LONG).show()
            }
    }
    fun getcurrentuserid():String{
        var currentuser=FirebaseAuth.getInstance().currentUser
        var currentuserid=""
        if(currentuser!=null){
            currentuserid=currentuser.uid
        }
        return currentuserid
    }
}