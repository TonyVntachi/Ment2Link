package com.example.academy_intern.ment2link.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.academy_intern.ment2link.R
import com.example.academy_intern.ment2link.pojos.Request

/**
 * Created by academy_intern on 2018/01/24.
 */
class RequestAdapter (private val context: Context, private val listRequests: ArrayList<Request>) : RecyclerView.Adapter<RequestAdapter.MyViewHolder>() {

    private var requestList: List<Request>? = null


    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var requestName: TextView
        var requestDate: TextView
        //var thumbnail: ImageView

        init {
            requestName = view.findViewById(R.id.requestTitle)
            requestDate = view.findViewById(R.id.requestDate)
            //thumbnail = view.findViewById(R.id.thumbnail)

            view.setOnClickListener {
                // send selected contact in callback

            }
        }
    }


    init {
        this.requestList = listRequests
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context)
                .inflate(R.layout.request_list_row, parent, false)

        return MyViewHolder(itemView)
    }


    override fun getItemCount(): Int {
        return requestList!!.size
    }


    override fun onBindViewHolder(holder: MyViewHolder?, position: Int) {

        val request: Request = requestList!![position]

        holder!!.requestName.text = request.requestName1
        holder.requestDate.text = request.requestDate1


    }



}