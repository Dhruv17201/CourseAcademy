package com.example.courseacademy

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class RegisterPage : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReferenceFromUrl("https://courseacademy-6e3fb-default-rtdb.firebaseio.com/")
    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        var name:EditText=findViewById(R.id.name)
        var username:EditText=findViewById(R.id.uname)
        var phone:EditText=findViewById(R.id.phonenum)
        var pass:EditText=findViewById(R.id.password)
        var cpass:EditText=findViewById(R.id.confirmpassword)
        var signupbtn:Button=findViewById(R.id.signupbtn)

        signupbtn.setOnClickListener {
            if(TextUtils.isEmpty(name.text.toString()))
            {
                name.requestFocus()
                name.setError("Please Enter Name")
            }
            else if(TextUtils.isEmpty(username.text.toString()))
            {
                username.requestFocus()
                username.setError("Please Enter Username")
            }
            else if(TextUtils.isEmpty(phone.text.toString()))
            {
                phone.requestFocus()
                phone.setError("Please Enter Phone Number")
            }
            else if(TextUtils.isEmpty(pass.text.toString()))
            {
                pass.requestFocus()
                pass.setError("Please Enter Password")
            }
            else if(TextUtils.isEmpty(cpass.text.toString()))
            {
                cpass.requestFocus()
                cpass.setError("Please Enter Confrim Password")
            }
            else
            {
                if(pass.text.toString() != cpass.text.toString())
                {
                    Toast.makeText(applicationContext, "Password And Confrim Password Are Not Same", Toast.LENGTH_SHORT).show()
                    pass.setText("")
                    cpass.setText("")
                    pass.requestFocus()
                    cpass.requestFocus()
                }
                else
                {

                    reference.child("UserDetails").addListenerForSingleValueEvent(object : ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            var Phone=phone.text.toString()
                            var Name=name.text.toString()
                            var password=pass.text.toString()
                            var Uname= username.text.toString()
                            if (!snapshot.hasChild(Phone))
                            {
                                if(!snapshot.hasChild(Uname)) {
                                    reference.child("UserDetails").child(Uname).child("Name")
                                        .setValue(Name)
                                    reference.child("UserDetails").child(Uname).child("UserName")
                                        .setValue(Uname)
                                    reference.child("UserDetails").child(Uname).child("Password")
                                        .setValue(password)
                                    reference.child("UserDetails").child(Uname).child("Phone")
                                        .setValue(Phone)
                                    Toast.makeText(
                                        applicationContext,
                                        "Registered Successfully ",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    var movetologin: Intent =
                                        Intent(applicationContext, LoginPage::class.java)
                                    startActivity(movetologin)
                                    finish()
                                }
                                else{
                                    Toast.makeText(applicationContext, "USerName Already Exists", Toast.LENGTH_SHORT).show()
                                }
                            }
                            else{
                                Toast.makeText(applicationContext, "Phone Number Already Exists", Toast.LENGTH_SHORT).show()
                            }
                        }

                        override fun onCancelled(error: DatabaseError) {
                            TODO("Not yet implemented")
                        }
                    })
                }
            }
        }
        var redirectlogin:Button=findViewById(R.id.redirectloginbtn)
        redirectlogin.setOnClickListener {
            var redirecttologin:Intent= Intent(applicationContext,LoginPage::class.java)
            startActivity(redirecttologin)
            finish()
        }
    }
}