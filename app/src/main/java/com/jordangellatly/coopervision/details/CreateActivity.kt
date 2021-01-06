package com.jordangellatly.coopervision.details

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemical
import kotlinx.android.synthetic.main.activity_create.*

class CreateActivity : AppCompatActivity() {
    private lateinit var myRef: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        setSupportActionBar(toolbar_create)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""

        btn_create.setBackgroundResource(R.drawable.button_orange)

        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("chemicals")

        btn_create.setOnClickListener {
            val bottleCount = java.lang.Long.parseLong(et_bottle_count_value.text.toString())
            val casNumber = et_cas_number_value.text.toString()
            val expirationDate = et_exp_date_value.text.toString()
            val locationInLab = et_location_value.text.toString()
            val lotOrderNumber = et_lot_order_value.text.toString()
            val manufacturer = et_manufacturer_value.text.toString()
            val materialName = et_name.text.toString()
            val receiveDate = et_rec_date_value.text.toString()
            val type = et_type_value.text.toString()
            writeNewChemical(bottleCount, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type)
            finish()
        }
    }

    private fun writeNewChemical(bottleCount: Long, casNumber: String, expirationDate: String, locationInLab: String, lotOrderNumber: String, manufacturer: String, materialName: String, receiveDate: String, type: String) {
        val lastChemical: Chemical = intent.getParcelableExtra("lastChemical")!!
        val id = lastChemical.id + 1
        val chemical = Chemical(bottleCount, id, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type)
        myRef.child(chemical.id.toString()).setValue(chemical)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
