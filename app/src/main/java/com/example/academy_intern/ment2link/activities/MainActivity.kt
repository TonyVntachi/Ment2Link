package com.example.academy_intern.ment2link.activities

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import com.example.academy_intern.ment2link.R
import ee.subscribe.gooeyloader.GooeyLoaderView

class MainActivity : AppCompatActivity() {

    internal var SPLASH_TIME_OUT = 9000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //this two lines hide the title bar of the activity
        val actionBar = supportActionBar
        actionBar!!.hide()

        var loadingAnimation = findViewById<GooeyLoaderView>(R.id.appLoader)




        //timer object and run thread for the splash screen and hides the title bar
//        requestWindowFeature(Window.FEATURE_NO_TITLE);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
       // setContentView(R.layout.activity_splash)
        Handler().postDelayed(
                {
                    startActivity(Intent(this, Login::class.java))
                    finish()
                }, SPLASH_TIME_OUT.toLong())
















        //find the view of a button and set onClick
      /*  val btn_click_me = findViewById<View>(R.id.button2) as Button

        btn_click_me.setOnClickListener(clickListener)*/


    }

    /*
    val clickListener = View.OnClickListener { view ->

        val i = Intent(this, SplashActivity::class.java)
        startActivity(i)
    }
     */
}
