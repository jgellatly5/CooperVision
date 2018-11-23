package com.jordangellatly.coopervision.details

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.FragmentPagerAdapter
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemicals
import kotlinx.android.synthetic.main.activity_detail.*
import org.parceler.Parcels

class DetailActivity : AppCompatActivity() {
    internal lateinit var detailPagerAdapter: FragmentPagerAdapter

    private var bundle: Bundle? = null
    private var colorChoice: Int = 0

    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setDetailTheme()
        setContentView(R.layout.activity_detail)

        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("chemicals")

        initToolbarColor()

        detailPagerAdapter = DetailPagerAdapter(supportFragmentManager, this@DetailActivity)
        view_pager.adapter = detailPagerAdapter
        tab_layout.setupWithViewPager(view_pager)
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
        bundle = intent.extras
        val length = 4
        colorChoice = bundle!!.getInt("position") % length
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
                .setPositiveButton("Yes") { dialogInterface, i -> removeChemical() }
                .setNegativeButton("No") { dialogInterface, i -> Toast.makeText(this@DetailActivity, "Cancel", Toast.LENGTH_SHORT).show() }
        val dialog = builder.create()
        dialog.show()
    }

    private fun removeChemical() {
        val chemical = Parcels.unwrap<Chemicals>(intent.getParcelableExtra<Parcelable>("chemical"))
        myRef!!.child(chemical.id.toString()).removeValue()
        val returnIntent = Intent()
        bundle!!.putString("intent", "remove")
        returnIntent.putExtras(bundle!!)
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        return super.onCreateOptionsMenu(menu)
    }
}
