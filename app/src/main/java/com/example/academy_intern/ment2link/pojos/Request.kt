package com.example.academy_intern.ment2link.pojos

/**
 * Created by academy_intern on 2018/01/24.
 */
class Request(var requestName: String, var requestDate: String) {


    var requestName1: String?



    set(requestNameVal)
    {
        requestName1 = requestNameVal!!.toLowerCase()
    }


        get() = requestName1



    var requestDate1: String? = null

        get() = requestDate1

}