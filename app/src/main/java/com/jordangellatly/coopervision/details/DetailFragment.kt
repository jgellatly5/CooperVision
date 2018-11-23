package com.jordangellatly.coopervision.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Parcelable
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.OnClick
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemicals
import kotlinx.android.synthetic.main.fragment_detail.*
import org.parceler.Parcels

class DetailFragment : Fragment() {
    private lateinit var chemicalFromIntent: Chemicals

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageColor()
        chemicalFromIntent = Parcels.unwrap<Chemicals>(activity!!.intent.getParcelableExtra<Parcelable>("chemical"))
        tv_name.text = chemicalFromIntent.materialName
        tv_location_value.text = chemicalFromIntent.locationInLab
        tv_rec_date_value.text = chemicalFromIntent.receiveDate
        tv_exp_date_value.text = chemicalFromIntent.expirationDate
        tv_lot_order_value.text = chemicalFromIntent.lotOrderNumber
        tv_bottle_count_value.text = chemicalFromIntent.bottleCount!!.toString()
        tv_cas_number_value.text = chemicalFromIntent.casNumber
        tv_manufacturer_value.text = chemicalFromIntent.manufacturer
        tv_type_value.text = chemicalFromIntent.type
    }

    @OnClick(R.id.btn_request)
    fun requestPurchase() {
        val intentRequest = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jgellatly5@gmail.com", null))
        intentRequest.putExtra(Intent.EXTRA_SUBJECT, "Request Order for: " + chemicalFromIntent.materialName)
        intentRequest.putExtra(Intent.EXTRA_TEXT, "Please place an order for this chemical: " + chemicalFromIntent.materialName)
        startActivity(Intent.createChooser(intentRequest, "Please choose an email client..."))
        activity!!.finish()
    }

    private fun initImageColor() {
        val bundle = activity!!.intent.extras
        val length = 4
        val colorChoice = bundle!!.getInt("position") % length

        when (colorChoice) {
            0 -> {
                image.setImageResource(R.drawable.cooper_drop_orange)
                btn_request.setBackgroundResource(R.drawable.button_orange)
            }
            1 -> {
                image.setImageResource(R.drawable.cooper_drop_cyan)
                btn_request.setBackgroundResource(R.drawable.button_cyan)
            }
            2 -> {
                image.setImageResource(R.drawable.cooper_drop_red)
                btn_request.setBackgroundResource(R.drawable.button_red)
            }
            3 -> {
                image.setImageResource(R.drawable.cooper_drop_purple)
                btn_request.setBackgroundResource(R.drawable.button_purple)
            }
        }
    }

    companion object {
        private val TAG = "DetailFragment"
        private val ARG_PARAM1 = "int"
        private val ARG_PARAM2 = "title"

        fun newInstance(title: String, page: Int): DetailFragment {
            val fragment = DetailFragment()
            val args = Bundle()
            args.putInt(ARG_PARAM1, page)
            args.putString(ARG_PARAM2, title)
            fragment.arguments = args
            return fragment
        }
    }
}
