package com.jordangellatly.coopervision.search

import android.app.Activity
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.firebase.database.*
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.details.CreateActivity
import com.jordangellatly.coopervision.details.DetailActivity
import com.jordangellatly.coopervision.models.Chemical
import kotlinx.android.synthetic.main.activity_list.*
import java.util.*

class ListActivity : AppCompatActivity(), ChemicalAdapter.ChemicalAdapterListener {
    private val chemicalArrayList = ArrayList<Chemical>()
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

        fab.setOnClickListener { createChemical() }

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

    private fun createChemical() {
        val chemicals = chemicalArrayList[chemicalArrayList.size - 1]
        val bundle = Bundle().apply {
            putParcelable("lastChemical", chemicals)
        }
        val intent = Intent(this@ListActivity, CreateActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivity(intent)
    }

    private fun getFirebaseData(dataSnapshot: DataSnapshot) {
        val chemicals = dataSnapshot.getValue(Chemical::class.java)
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
        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchView = (menu.findItem(R.id.menu_toolbarsearch).actionView as SearchView).apply {
            queryHint = "Enter Chemical Name"
            setSearchableInfo(searchManager.getSearchableInfo(componentName))
            maxWidth = Integer.MAX_VALUE
        }

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

    override fun onChemicalSelected(chemical: Chemical, position: Int) {
        val bundle = Bundle().apply {
            putInt("position", position)
            putParcelable("chemical", chemical)
        }
        val intent = Intent(this@ListActivity, DetailActivity::class.java).apply {
            putExtras(bundle)
        }
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            when (data?.getStringExtra("intent")) {
                "update" -> {
                    progress_bar.visibility = ProgressBar.VISIBLE
                    chemicalAdapter.update()
                    fetchListData()
                }
                "remove" -> {
                    val position = data.getIntExtra("position", -1)
                    chemicalAdapter.removeAt(position)
                }
            }
        }
    }

    companion object {
        private const val REQUEST_CODE = 1
    }
}
