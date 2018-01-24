package com.example.academy_intern.ment2link.activities

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.*
import com.example.academy_intern.ment2link.drawers.MentorNavigatationDrawer
import com.example.academy_intern.ment2link.pojos.PopulateDataBase
import com.example.academy_intern.ment2link.pojos.PopulateMembers
import com.example.academy_intern.ment2link.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class Login : AppCompatActivity() {


    var fbAuth = FirebaseAuth.getInstance()
    private var email_adress: String? = null
    private var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //val actionBar = supportActionBar
       // actionBar!!.hide()


        //Button OnClick() method
        var inputEmail = findViewById<EditText>(R.id.etUserName)
        var progress = findViewById<ProgressBar>(R.id.progressBar)
        var inputPassword = findViewById<EditText>(R.id.etPassword)

        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var btnSignUp = findViewById<TextView>(R.id.txtSignUp)
        var btnReset = findViewById<TextView>(R.id.tvFogotPassword)

        btnSignUp.setOnClickListener {
            //Sign in funtion called with inputs parameters
//            val i = Intent(this, WebViewActivity::class.java)
//            startActivity(i)

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://thinkrise.com/users/new"))
            val browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice")
            startActivity(browserChooserIntent)
        }


        btnReset.setOnClickListener {
            //Sign in funtion called with inputs parameters
            startActivity(Intent(this, PasswordRestActivity::class.java))
        }

        btnLogin.setOnClickListener {
            email_adress = inputEmail?.text.toString()
            pass = inputPassword?.text.toString()

            if(TextUtils.isEmpty(email_adress)){
                Toast.makeText(this,"Enter E-mail address",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Enter Password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            progress.visibility

            fbAuth!!.signInWithEmailAndPassword(email_adress!!, pass!!).addOnCompleteListener(this, OnCompleteListener { task ->
                //If logged in successfully then true
                if(task.isSuccessful){

                    if(pass!!.length < 6)
                    {
                        Toast.makeText(this,"Enter > 6 digits",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Successfully logged in",Toast.LENGTH_LONG).show()
                    }

                    val fbRef = FirebaseDatabase.getInstance().getReference("Role")
                    val fbRoleId = fbRef.push().key

                    val populateDataBase = PopulateDataBase( "1", "Half Man", "123456", "Mentor")

                    val populateDataBase2 = PopulateMembers("Vusi", "Zungu","Full Stack Dev", "JHB CBD", "Software Development", "vusi@thedigitalacademy")


                    fbRef.child(fbRoleId).child("User").child("Mentor").setValue(populateDataBase)



                    //val fbRef2 = FirebaseDatabase.getInstance().getReference().ref.ref.

                    //val fbRoleId2 = fbRef2.push().key

                    //fbRef2.child(fbRoleId2).child("Mentor").setValue(populateDataBase2)


                    var intent = Intent(this, MentorNavigatationDrawer::class.java)
                    intent.putExtra("id",fbAuth.currentUser?.email)
                    startActivity(intent)
                }else
                {
                    //If false the show error message
                    Toast.makeText(this,"Unsuccessfully",Toast.LENGTH_LONG).show()
                    // showMessage(view," Error occured: ${task.exception?.message}")
                }
            })


        }


    }
}
