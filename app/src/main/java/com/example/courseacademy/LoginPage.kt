package com.example.courseacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class LoginPage : AppCompatActivity() {
    val database = FirebaseDatabase.getInstance()
    val reference = database.getReferenceFromUrl("https://courseacademy-6e3fb-default-rtdb.firebaseio.com/")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login_page)
        var signup:Button=findViewById(R.id.signupredirect)
        var btnlogin:Button=findViewById(R.id.loginbtn)
        var email:EditText=findViewById(R.id.Emailid)
        var pass:EditText=findViewById(R.id.password)
        signup.setOnClickListener {
            var Signup:Intent= Intent(applicationContext,RegisterPage::class.java)
            startActivity(Signup)
        }
        btnlogin.setOnClickListener {
            if(TextUtils.isEmpty(email.text.toString()))
            {
                email.setError("Please Fill Out UserName")
                email.requestFocus()
            }
            else if(TextUtils.isEmpty(pass.text.toString()))
            {
                pass.setError("Please Fill Out UserName")
                pass.requestFocus()
            }
            else{
                var getemail=email.text.toString()
                var getpass=pass.text.toString()
                reference.child("UserDetails").addListenerForSingleValueEvent(object :ValueEventListener{
                    override fun onDataChange(snapshot: DataSnapshot) {
                        reference.child("UserDetails").child(getemail)
                        /*if()
                        {

                        }*/
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }

                })


            }
        }

    }
}