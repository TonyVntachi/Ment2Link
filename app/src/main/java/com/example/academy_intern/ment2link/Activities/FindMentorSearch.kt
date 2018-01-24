package com.example.academy_intern.ment2link.Activities

import android.app.SearchManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.*
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.android.volley.Response
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonArrayRequest
import com.example.academy_intern.ment2link.Adapter.ContactsAdapter
import com.example.academy_intern.ment2link.MyDividerItemDecoration
import com.example.academy_intern.ment2link.Pojo.Contact
import com.example.academy_intern.ment2link.Pojo.MyApplication
import com.example.academy_intern.ment2link.R
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray

class FindMentorSearch : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private var recyclerView: RecyclerView? = null
    private var contactList: ArrayList<Contact>? = null
    private var mAdapter: ContactsAdapter? = null
    private var searchView: SearchView? = null

    // url to fetch contacts json
    private val URL = "https://api.androidhive.info/json/contacts.json"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_find_mentor_search)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        //setSupportActionBar(toolbar)


        // toolbar fancy stuff
        //supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        //supportActionBar!!.setTitle(R.string.toolbar_title)

        recyclerView = findViewById<View>(R.id.recycler_view) as RecyclerView?
        contactList = ArrayList<Contact>()
        mAdapter = ContactsAdapter(this, contactList as ArrayList<Contact>, this)


        // white background notification bar
        whiteNotificationBar(findViewById<View>(R.id.recycler_view));

        val mLayoutManager = LinearLayoutManager(applicationContext)
        recyclerView!!.setLayoutManager(mLayoutManager)
        recyclerView!!.setItemAnimator(DefaultItemAnimator())
        recyclerView!!.addItemDecoration(MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36))
        recyclerView!!.setAdapter(mAdapter)




    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        // Associate searchable configuration with the SearchView
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search)
                .getActionView() as SearchView?
        searchView?.setSearchableInfo(searchManager
                .getSearchableInfo(componentName))
        //searchView?.maxWidth ?: = Integer.MAX_VALUE

        // listening to search query text change
        searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                // filter recycler view when query submitted
                mAdapter?.getFilter()!!.filter(query)
                return false
            }

            override fun onQueryTextChange(query: String): Boolean {
                // filter recycler view when text is changed
                mAdapter?.filter?.filter(query)
                return false
            }
        })
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        val id = item.getItemId()


        return if (id == R.id.action_search) {
            true
        } else super.onOptionsItemSelected(item)

    }


    override fun onBackPressed() {
        // close search view on back button pressed
        if (!searchView!!.isIconified()) {
            searchView!!.setIconified(true)
            return
        }
        super.onBackPressed()
    }


    private fun whiteNotificationBar(view: View) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            var flags = view.systemUiVisibility
            flags = flags or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            view.systemUiVisibility = flags
            window.statusBarColor = Color.WHITE
        }
    }


    fun onContactSelected(contact: Contact) {
        Toast.makeText(applicationContext, "Selected: " + contact.getName() + ", " + contact.getPhone(), Toast.LENGTH_LONG).show()
    }

}
