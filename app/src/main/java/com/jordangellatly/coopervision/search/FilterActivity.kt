package com.jordangellatly.coopervision.search

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.jordangellatly.coopervision.R
import kotlinx.android.synthetic.main.activity_filter.*

class FilterActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var intentWithFilter: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_filter)

        val adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        location_selection.adapter = adapter
        location_selection.onItemSelectedListener = this

        intentWithFilter = Intent(this@FilterActivity, ListActivity::class.java)
        btn_search.setOnClickListener { startActivity(intentWithFilter) }
        val intentSearchAll = Intent(this@FilterActivity, ListActivity::class.java)
        btn_search_all.setOnClickListener { startActivity(intentSearchAll) }
    }

    override fun onItemSelected(adapterView: AdapterView<*>, view: View, i: Int, l: Long) {
        val selection = adapterView.getItemAtPosition(i).toString()
        intentWithFilter.putExtra("filter", selection)
    }

    override fun onNothingSelected(adapterView: AdapterView<*>) {

    }
}
