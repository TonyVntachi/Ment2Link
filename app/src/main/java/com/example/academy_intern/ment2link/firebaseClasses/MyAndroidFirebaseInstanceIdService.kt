package com.example.academy_intern.ment2link.firebaseClasses

import android.util.Log
import com.google.firebase.iid.FirebaseInstanceId
import com.google.firebase.iid.FirebaseInstanceIdService

/**
 * Created by academy_intern on 2018/01/15.
 */
class MyAndroidFirebaseInstanceIdService : FirebaseInstanceIdService() {

    override fun onTokenRefresh() {
        //Get hold of the registration token
        val refreshedToken = FirebaseInstanceId.getInstance().token
        //Log the token

        Log.d(TAG, "Refreshed token: " + refreshedToken!!)
    }

    private fun sendRegistrationToServer(token: String) {
        //Implement this method if you want to store the token on your server

    }

    companion object {

        private val TAG = "MyAndroidFCMIIDService"
    }
}