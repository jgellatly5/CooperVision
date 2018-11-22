package com.jordangellatly.coopervision.details

import android.os.Bundle
import android.os.Parcelable
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemicals
import kotlinx.android.synthetic.main.activity_create.*
import org.parceler.Parcels

class CreateActivity : AppCompatActivity() {

    private var bottleCount: Long? = 0
    private var casNumber: String? = null
    private var expirationDate: String? = null
    private var locationInLab: String? = null
    private var lotOrderNumber: String? = null
    private var manufacturer: String? = null
    private var materialName: String? = null
    private var receiveDate: String? = null
    private var type: String? = null

    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)

        setSupportActionBar(toolbar_create)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.title = ""

        btn_create.setBackgroundResource(R.drawable.button_orange)

        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("chemicals")

        btn_create.setOnClickListener { v ->
            bottleCount = java.lang.Long.parseLong(et_bottle_count_value.text.toString())
            casNumber = et_cas_number_value.text.toString()
            expirationDate = et_exp_date_value.text.toString()
            locationInLab = et_location_value.text.toString()
            lotOrderNumber = et_lot_order_value.text.toString()
            manufacturer = et_manufacturer_value.text.toString()
            materialName = et_name.text.toString()
            receiveDate = et_rec_date_value.text.toString()
            type = et_type_value.text.toString()
            writeNewChemical(bottleCount, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type)
            finish()
        }
    }

    private fun writeNewChemical(bottleCount: Long?, casNumber: String?, expirationDate: String?, locationInLab: String?, lotOrderNumber: String?, manufacturer: String?, materialName: String?, receiveDate: String?, type: String?) {
        val lastChemical = Parcels.unwrap<Chemicals>(intent.getParcelableExtra<Parcelable>("lastChemical"))
        val id = lastChemical.getId()!! + 1
        val chemical = Chemicals(bottleCount, id, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type)
        myRef!!.child(chemical.getId().toString()).setValue(chemical)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> finish()
            else -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
