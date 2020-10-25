package com.jordangellatly.coopervision.search

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import com.google.firebase.database.*
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.details.CreateActivity
import com.jordangellatly.coopervision.details.DetailActivity
import com.jordangellatly.coopervision.models.Chemicals
import kotlinx.android.synthetic.main.activity_list.*
import org.parceler.Parcels
import java.util.*

class ListActivity : AppCompatActivity(), ChemicalAdapter.ChemicalAdapterListener {
    private val REQUEST_CODE = 1
    private val chemicalArrayList = ArrayList<Chemicals>()
    private lateinit var chemicalAdapter: ChemicalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)

        setSupportActionBar(toolbar_search)
        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            title = ""
        }
        tv_empty_list.visibility = View.INVISIBLE
        progress_bar.visibility = ProgressBar.VISIBLE

        chemicalAdapter = ChemicalAdapter(chemicalArrayList, this@ListActivity)

        fab.setOnClickListener { addChemical() }

        fetchListData()
    }

    private fun fetchListData() {
        val database = FirebaseDatabase.getInstance()
        val myRef = database.getReference("chemicals")

        val filter = intent.getStringExtra("filter")
        if (filter != null) {
            val filterData = myRef.orderByChild("locationInLab").equalTo(filter)
            filterData.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    getFirebaseData(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {

                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        } else {
            myRef.addChildEventListener(object : ChildEventListener {
                override fun onChildAdded(dataSnapshot: DataSnapshot, s: String?) {
                    getFirebaseData(dataSnapshot)
                }

                override fun onChildChanged(dataSnapshot: DataSnapshot, s: String?) {

                }

                override fun onChildRemoved(dataSnapshot: DataSnapshot) {

                }

                override fun onChildMoved(dataSnapshot: DataSnapshot, s: String?) {

                }

                override fun onCancelled(databaseError: DatabaseError) {

                }
            })
        }
    }

    private fun addChemical() {
        val intent = Intent(this@ListActivity, CreateActivity::class.java)
        val bundle = Bundle()
        val chemicals = chemicalArrayList[chemicalArrayList.size - 1]
        bundle.putParcelable("lastChemical", Parcels.wrap(chemicals))
        intent.putExtras(bundle)
        startActivity(intent)
    }

    private fun getFirebaseData(dataSnapshot: DataSnapshot) {
        val chemicals = dataSnapshot.getValue(Chemicals::class.java)
        if (chemicals != null) {
            chemicalArrayList.add(chemicals)
        }

        chemical_list.apply {
            adapter = chemicalAdapter
            layoutManager = LinearLayoutManager(this@ListActivity)
        }
        progress_bar.visibility = ProgressBar.INVISIBLE
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_search, menu)
        val mSearchMenuItem = menu.findItem(R.id.menu_toolbarsearch)
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = mSearchMenuItem.actionView as SearchView
        searchView.queryHint = "Enter Chemical Name"
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Integer.MAX_VALUE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                chemicalAdapter.filter.filter(s)
                if (chemicalAdapter.itemCount == 0) {
                    tv_empty_list.visibility = View.VISIBLE
                } else {
                    tv_empty_list.visibility = View.INVISIBLE
                }
                return false
            }
        })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onChemicalSelected(chemicals: Chemicals, position: Int) {
        val bundle = Bundle().apply {
            putInt("position", position)
            putParcelable("chemical", Parcels.wrap(chemicals))
        }
        val intent = Intent(this@ListActivity, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val position = data!!.getIntExtra("position", -1)
            val stringExtra = data.getStringExtra("intent")
            if (stringExtra == "remove") {
                chemicalAdapter.removeAt(position)
            }
            if (stringExtra == "update") {
                progress_bar.visibility = ProgressBar.VISIBLE
                chemicalAdapter.update()
                fetchListData()
            }
        }
    }
}
