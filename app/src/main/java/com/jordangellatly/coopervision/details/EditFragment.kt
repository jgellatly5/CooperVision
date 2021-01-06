package com.jordangellatly.coopervision.details

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemical
import kotlinx.android.synthetic.main.fragment_edit.*
import java.util.*

class EditFragment : Fragment() {
    private lateinit var myRef: DatabaseReference
    private lateinit var chemicalFromIntent: Chemical
    private lateinit var bundleFromListActivity: Bundle

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val database = FirebaseDatabase.getInstance()
        myRef = database.getReference("chemicals")
        chemicalFromIntent = requireActivity().intent.getParcelableExtra("chemical")!!
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_edit, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageColor()

        btn_update.setOnClickListener { update() }

        et_name.setText(chemicalFromIntent.materialName)
        et_location_value.setText(chemicalFromIntent.locationInLab)
        et_rec_date_value.setText(chemicalFromIntent.receiveDate)
        et_exp_date_value.setText(chemicalFromIntent.expirationDate)
        et_lot_order_value.setText(chemicalFromIntent.lotOrderNumber)
        et_bottle_count_value.setText(chemicalFromIntent.bottleCount.toString())
        et_cas_number_value.setText(chemicalFromIntent.casNumber)
        et_manufacturer_value.setText(chemicalFromIntent.manufacturer)
        et_type_value.setText(chemicalFromIntent.type)
    }

    private fun initImageColor() {
        bundleFromListActivity = requireActivity().intent.extras!!
        val length = 4
        when (bundleFromListActivity.getInt("position") % length) {
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
        myRef.child(chemicalFromIntent.id.toString()).updateChildren(chemicalUpdates)

        bundleFromListActivity.putString("intent", "update")
        val backToListIntent = Intent().apply {
            putExtras(bundleFromListActivity)
        }
        requireActivity().setResult(Activity.RESULT_OK, backToListIntent)
        requireActivity().finish()
    }

    companion object {
        private val TAG = "EditFragment"
        private const val ARG_PARAM1 = "int"
        private const val ARG_PARAM2 = "title"

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
