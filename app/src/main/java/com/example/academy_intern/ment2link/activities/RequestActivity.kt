package com.example.academy_intern.ment2link.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.academy_intern.ment2link.R
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.LinearLayout
import com.example.academy_intern.ment2link.adapter.RequestAdapter
import com.example.academy_intern.ment2link.pojos.Request


class RequestActivity : AppCompatActivity() {

    var listRequests: ArrayList<Request> = ArrayList<Request>()
    //var recyclerView: RecyclerView? = null
    var mAdapter: RequestAdapter? = null

    lateinit var linearLayoutManager: LinearLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_request)


        //Bind the recyclerview
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView) as RecyclerView


        //Add a LayoutManager
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)


        val request: Request = Request("Project Management","28/01/2018")

        listRequests.add(0,request)

        recyclerView.adapter = RequestAdapter(this,listRequests)

       // mAdapter = RequestAdapter(this, listRequests)


    }
}
