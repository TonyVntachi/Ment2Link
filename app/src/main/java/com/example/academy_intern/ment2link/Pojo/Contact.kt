package com.example.academy_intern.ment2link.Pojo

/**
 * Created by academy_intern on 2018/01/ata18.
 */
class Contact
{

    internal var name: String? = null
    internal var image: String? = null
    internal var phone: String? = null

    fun Contact() {}

    fun getName(): String? {
        return name
    }

    fun getImage(): String? {
        return image
    }

    fun getPhone(): String? {
        return phone
    }
}