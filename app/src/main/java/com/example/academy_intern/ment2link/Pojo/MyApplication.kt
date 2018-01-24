package com.example.academy_intern.ment2link.Pojo

import android.app.Application
import android.text.TextUtils

import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.toolbox.Volley

/**
 * Created by academy_intern on 2018/01/18.
 */

class MyApplication : Application() {

    private var mRequestQueue: RequestQueue? = null

    val requestQueue: RequestQueue
        get() {
            if (mRequestQueue == null) {
                mRequestQueue = Volley.newRequestQueue(applicationContext)
            }

            return this!!.mRequestQueue!!
        }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    fun <T> addToRequestQueue(req: Request<T>, tag: String) {
        // set the default tag if tag is empty
        req.tag = if (TextUtils.isEmpty(tag)) TAG else tag
        requestQueue.add(req)
    }

    fun <T> addToRequestQueue(req: Request<T>) {
        req.tag = TAG
        requestQueue.add(req)
    }

    fun cancelPendingRequests(tag: Any) {
        if (mRequestQueue != null) {
            mRequestQueue!!.cancelAll(tag)
        }
    }

    companion object {

        val TAG = MyApplication::class.java
                .simpleName

        @get:Synchronized
        var instance: MyApplication? = null
            private set
    }
}
