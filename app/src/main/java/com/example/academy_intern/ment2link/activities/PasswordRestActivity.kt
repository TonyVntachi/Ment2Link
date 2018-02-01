package com.example.academy_intern.ment2link.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.example.academy_intern.ment2link.R
import com.google.firebase.auth.FirebaseAuth

class PasswordRestActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_rest)

        val actionBar = supportActionBar
        actionBar!!.hide()


        // set input feilds views
        var inputEmail = findViewById<EditText>(R.id.email)
        var btnReset = findViewById<Button>(R.id.btn_reset_password)
        //var btnBack = findViewById<Button>(R.id.btn_back)
        var progress_bar = findViewById<ProgressBar>(R.id.progressBar)

        var auth = FirebaseAuth.getInstance()

//        btnBack.setOnClickListener {
//            finish()
//        }


        //click button to start password rest
        btnReset.setOnClickListener {
            var email: String?


            //validate email address is not empty
            email = inputEmail?.text.toString()
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(applicationContext, "Enter your registered email id", Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            progress_bar.visibility

            //use firebase authentication to send email rest link to email address if email found
            auth.sendPasswordResetEmail(email)

                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this, "We have sent you instructions to reset your password", Toast.LENGTH_LONG).show()
                        } else {
                            Toast.makeText(this, "Failed to send reset email", Toast.LENGTH_LONG).show()
                        }
                        progress_bar.visibility
                    }

        }


    }
}
