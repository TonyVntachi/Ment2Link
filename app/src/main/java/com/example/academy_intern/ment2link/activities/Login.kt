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

    //firebase database instance
    var fbAuth = FirebaseAuth.getInstance()

    //varibles to be used in sign in
    private var email_adress: String? = null
    private var pass: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //val actionBar = supportActionBar
       // actionBar!!.hide()


        //input fields
        var inputEmail = findViewById<EditText>(R.id.etUserName)
        var progress = findViewById<ProgressBar>(R.id.progressBar)
        var inputPassword = findViewById<EditText>(R.id.etPassword)

        //buttons
        var btnLogin = findViewById<Button>(R.id.btnLogin)
        var btnSignUp = findViewById<TextView>(R.id.txtSignUp)
        var btnReset = findViewById<TextView>(R.id.tvFogotPassword)



        btnSignUp.setOnClickListener {
            //Sign up direct to rise offical site to join

            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://thinkrise.com/users/new"))
            val browserChooserIntent = Intent.createChooser(browserIntent, "Choose browser of your choice")
            startActivity(browserChooserIntent)
        }


        btnReset.setOnClickListener {
            //Reset the users password by using the users email
            startActivity(Intent(this, PasswordRestActivity::class.java))
        }

        btnLogin.setOnClickListener {
            email_adress = inputEmail?.text.toString()
            pass = inputPassword?.text.toString()


            //test to see if input field are supplied the correct information
            if(TextUtils.isEmpty(email_adress)){
                Toast.makeText(this,"Enter E-mail address",Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if(TextUtils.isEmpty(pass)){
                Toast.makeText(this,"Enter Password",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            progress.visibility


            //Pass login Credentials to firebase using email varification
            fbAuth!!.signInWithEmailAndPassword(email_adress!!, pass!!).addOnCompleteListener(this, OnCompleteListener { task ->
                //If logged in successfully then true
                if(task.isSuccessful){

                    //validate password length
                    if(pass!!.length < 6)
                    {
                        Toast.makeText(this,"Enter > 6 digits",Toast.LENGTH_LONG).show()
                    }else{
                        Toast.makeText(this,"Successfully logged in",Toast.LENGTH_LONG).show()
                    }

                    //select role of user
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
