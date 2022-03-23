package com.example.courseacademy

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

class RegisterPage : AppCompatActivity() {

    val database = FirebaseDatabase.getInstance()
    val reference = database.getReferenceFromUrl("https://courseacademy-6e3fb-default-rtdb.firebaseio.com/")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_page)
        var name:EditText=findViewById(R.id.name)
        var uname:EditText=findViewById(R.id.username)
        var email:EditText=findViewById(R.id.emailid)
        var pass:EditText=findViewById(R.id.password)
        var cpass:EditText=findViewById(R.id.confirmpassword)
        var signupbtn:Button=findViewById(R.id.signupbtn)

        signupbtn.setOnClickListener {
            if(TextUtils.isEmpty(name.text.toString()))
            {
                name.requestFocus()
                name.setError("Please Enter Name")
            }
            else if(TextUtils.isEmpty(uname.text.toString()))
            {
                uname.requestFocus()
                uname.setError("Please Enter UserName")
            }
            else if(TextUtils.isEmpty(email.text.toString()))
            {
                email.requestFocus()
                email.setError("Please Enter EmailID")
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
                    var emailid=email.text.toString()
                    var Name=name.text.toString()
                    var username=uname.text.toString()
                    var password=pass.text.toString()
                    reference.child("UserDetails").addListenerForSingleValueEvent(object :
                        ValueEventListener {
                        override fun onDataChange(snapshot: DataSnapshot) {
                            if(snapshot.hasChild(emailid))
                            {
                                Toast.makeText(applicationContext, "Email Already Exist Try With New Email ID", Toast.LENGTH_SHORT).show()
                            }
                            else if (snapshot.hasChild(username)){
                                Toast.makeText(applicationContext, "UserName Already Exist Try With New UserName", Toast.LENGTH_SHORT).show()
                            }
                            else{
                                reference.child("UserDetails").child(emailid).child("Name").setValue(Name)
                                reference.child("UserDetails").child(emailid).child("Email ID").setValue(emailid)
                                reference.child("UserDetails").child(emailid).child("Username").setValue(username)
                                reference.child("UserDetails").child(emailid).child("Password").setValue(password)
                                Toast.makeText(applicationContext, "Registered SuccessFully", Toast.LENGTH_LONG).show()
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
}