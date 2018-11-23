package com.jordangellatly.coopervision.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemicals
import kotlinx.android.synthetic.main.fragment_edit.*
import org.parceler.Parcels
import java.util.*

class EditFragment : Fragment() {

    private var title: String? = null
    private var page: Int = 0

    private var database: FirebaseDatabase? = null
    private var myRef: DatabaseReference? = null

    private var chemicalFromIntent: Chemicals? = null
    private var bundleFromListActivity: Bundle? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        page = arguments!!.getInt(ARG_PARAM1, 0)
        title = arguments!!.getString(ARG_PARAM2)

        database = FirebaseDatabase.getInstance()
        myRef = database!!.getReference("chemicals")
        chemicalFromIntent = Parcels.unwrap<Chemicals>(activity!!.intent.getParcelableExtra<Parcelable>("chemical"))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_edit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageColor()

        btn_update.setOnClickListener { v -> update() }

        et_name.setText(chemicalFromIntent!!.getMaterialName())
        et_location_value.setText(chemicalFromIntent!!.getLocationInLab())
        et_rec_date_value.setText(chemicalFromIntent!!.getReceiveDate())
        et_exp_date_value.setText(chemicalFromIntent!!.getExpirationDate())
        et_lot_order_value.setText(chemicalFromIntent!!.getLotOrderNumber())
        et_bottle_count_value.setText(chemicalFromIntent!!.getBottleCount()!!.toString())
        et_cas_number_value.setText(chemicalFromIntent!!.getCasNumber())
        et_manufacturer_value.setText(chemicalFromIntent!!.getManufacturer())
        et_type_value.setText(chemicalFromIntent!!.getType())
    }

    private fun initImageColor() {
        bundleFromListActivity = activity!!.intent.extras
        val length = 4
        val colorChoice = bundleFromListActivity!!.getInt("position") % length
        when (colorChoice) {
            0 -> {
                image.setImageResource(R.drawable.cooper_drop_orange)
                btn_update.setBackgroundResource(R.drawable.button_orange)
            }
            1 -> {
                image.setImageResource(R.drawable.cooper_drop_cyan)
                btn_update.setBackgroundResource(R.drawable.button_cyan)
            }
            2 -> {
                image.setImageResource(R.drawable.cooper_drop_red)
                btn_update.setBackgroundResource(R.drawable.button_red)
            }
            3 -> {
                image.setImageResource(R.drawable.cooper_drop_purple)
                btn_update.setBackgroundResource(R.drawable.button_purple)
            }
        }
    }

    private fun update() {
        val chemicalUpdates = HashMap<String, Any>()
        chemicalUpdates["bottleCount"] = java.lang.Long.parseLong(et_bottle_count_value.text.toString())
        chemicalUpdates["casNumber"] = et_cas_number_value.text.toString()
        chemicalUpdates["expirationDate"] = et_exp_date_value.text.toString()
        chemicalUpdates["locationInLab"] = et_location_value.text.toString()
        chemicalUpdates["lotOrderNumber"] = et_lot_order_value.text.toString()
        chemicalUpdates["manufacturer"] = et_manufacturer_value.text.toString()
        chemicalUpdates["materialName"] = et_name.text.toString()
        chemicalUpdates["receiveDate"] = et_rec_date_value.text.toString()
        chemicalUpdates["type"] = et_type_value.text.toString()
        myRef!!.child(chemicalFromIntent!!.getId().toString()).updateChildren(chemicalUpdates)

        val backToListIntent = Intent()
        bundleFromListActivity!!.putString("intent", "update")
        backToListIntent.putExtras(bundleFromListActivity!!)
        activity!!.setResult(Activity.RESULT_OK, backToListIntent)
        activity!!.finish()
    }

    companion object {

        private val TAG = "EditFragment"

        private val ARG_PARAM1 = "int"
        private val ARG_PARAM2 = "title"

        fun newInstance(title: String, page: Int): EditFragment {
            val fragment = EditFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, page)
            args.putString(ARG_PARAM2, title)
            fragment.arguments = args
            return fragment
        }
    }
}
