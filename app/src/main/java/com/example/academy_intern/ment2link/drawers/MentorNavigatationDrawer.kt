package com.example.academy_intern.ment2link.drawers

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import com.example.academy_intern.ment2link.activities.MentorProfile
import com.example.academy_intern.ment2link.R
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_new_navigatation_drawer_fix.*
import kotlinx.android.synthetic.main.app_bar_new_navigatation_drawer_fix.*
import android.content.DialogInterface
import android.widget.DatePicker
import com.example.academy_intern.ment2link.activities.RequestActivity
import com.example.academy_intern.ment2link.activities.CalenderActivity
import java.util.*


class MentorNavigatationDrawer : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    var cal = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_navigatation_drawer_fix)
        setSupportActionBar(toolbar)


        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener(this)
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.new_navigatation_drawer_fix, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        when (item.itemId) {
            R.id.action_settings -> return true
            else -> return super.onOptionsItemSelected(item)
        }
    }


    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        // Handle navigation view item clicks here.


        var auth = FirebaseAuth.getInstance()

        when (item.itemId) {
            R.id.nav_profile -> {
                val i = Intent(this, MentorProfile::class.java)
                startActivity(i)
            }
            R.id.nav_requests -> {

                val i = Intent(this, RequestActivity::class.java)
                startActivity(i)

            }
            R.id.nav_blocked -> {

            }
            R.id.nav_calender -> {

                //opens view to calender

                val i = Intent(this, CalenderActivity::class.java)
                startActivity(i)


            }
            R.id.nav_about -> {
                //val i = Intent(this, FindMentorSearch::class.java)
                ///startActivity(i)
            }
            R.id.nav_logout -> {

                //showMessage(view, "Logging out...")
                auth.signOut()

                auth.addAuthStateListener {
                    if (auth.currentUser == null) {
                        this.finish()
                    }
                }

            }
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }

    val date: DatePickerDialog.OnDateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth -> view.minDate = System.currentTimeMillis() - 1000 }


}
