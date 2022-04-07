package com.example.courseacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class LoginPage : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReferenceFromUrl("https://courseacademy-6e3fb-default-rtdb.firebaseio.com/")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        var signup:Button=findViewById(R.id.signupredirect)
        var btnlogin:Button=findViewById(R.id.loginbtn)
        var uname:EditText=findViewById(R.id.username)
        var pass:EditText=findViewById(R.id.password)
        signup.setOnClickListener {
            var Signup = Intent(applicationContext,RegisterPage::class.java)
            startActivity(Signup)
            finish()
        }
        btnlogin.setOnClickListener {
            if(TextUtils.isEmpty(uname.text.toString()))
            {
                uname.error = "Please Fill Out User Name"
                uname.requestFocus()
            }
            else if(TextUtils.isEmpty(pass.text.toString()))
            {
                pass.error = "Please Fill Out Password"
                pass.requestFocus()
            }
            else{
                var getuname=uname.text.toString()
                var getpass=pass.text.toString()
                reference.child("UserDetails").addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        if(snapshot.hasChild(getuname))
                        {
                            var storepass=snapshot.child(getuname).child("Password").getValue()
                            if(storepass == getpass)
                            {
                                //Toast.makeText(applicationContext, "$storepass", Toast.LENGTH_SHORT).show()
                             var movetologin:Intent = Intent(applicationContext,MainPage::class.java)
                                startActivity(movetologin)
                            }
                            else{
                                Toast.makeText(applicationContext, "Password Is Wrong Please Check It Again", Toast.LENGTH_SHORT).show()
                                  pass.requestFocus()
                                pass.setText("")
                            }
                        }
                        else
                        {
                            Toast.makeText(applicationContext, "Please Check Your UserName And Try Again !! ", Toast.LENGTH_SHORT).show()
                            uname.requestFocus()
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


            }
        }

    }
}