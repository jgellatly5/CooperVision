package com.jordangellatly.coopervision.details

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemical
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity() {
    private var colorChoice: Int = 0
    private lateinit var detailPagerAdapter: DetailPagerAdapter
    private lateinit var bundle: Bundle
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDetailTheme()
        setContentView(R.layout.activity_detail)

        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("chemicals")

        initToolbarColor()

        detailPagerAdapter = DetailPagerAdapter(this@DetailActivity)
        view_pager.adapter = detailPagerAdapter
        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when (position) {
                0 -> tab.text = "Details"
                else -> tab.text = "Edit"
            }
        }.attach()
    }

    private fun initToolbarColor() {
        setSupportActionBar(toolbar_detail)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""
        when (colorChoice) {
            0 -> toolbar_detail.setBackgroundColor(Color.parseColor("#e65100"))
            1 -> toolbar_detail.setBackgroundColor(Color.parseColor("#00bcd4"))
            2 -> toolbar_detail.setBackgroundColor(Color.parseColor("#e53935"))
            3 -> toolbar_detail.setBackgroundColor(Color.parseColor("#7b1fa2"))
        }
    }

    private fun setDetailTheme() {
        bundle = intent.extras!!
        val length = 4
        colorChoice = bundle.getInt("position") % length
        when (colorChoice) {
            0 -> setTheme(R.style.OrangeTheme)
            1 -> setTheme(R.style.CyanTheme)
            2 -> setTheme(R.style.RedTheme)
            3 -> setTheme(R.style.PurpleTheme)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            R.id.detail_remove -> createDialog()
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createDialog() {
        val builder = AlertDialog.Builder(this@DetailActivity)
        builder.setMessage("Are you sure you would like to remove this chemical from the inventory?")
                .setTitle("Warning")
                .setPositiveButton("Yes") { _, _ -> removeChemical() }
                .setNegativeButton("No") { _, _ -> Toast.makeText(this@DetailActivity, "Cancel", Toast.LENGTH_SHORT).show() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun removeChemical() {
        val chemical: Chemical? = intent.getParcelableExtra("chemical")
        myRef.child(chemical!!.id.toString()).removeValue()
        val returnIntent = Intent()
        bundle.putString("intent", "remove")
        returnIntent.putExtras(bundle)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
