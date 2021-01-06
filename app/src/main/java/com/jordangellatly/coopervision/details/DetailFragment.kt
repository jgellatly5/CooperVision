package com.jordangellatly.coopervision.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemical
import kotlinx.android.synthetic.main.fragment_detail.*

class DetailFragment : Fragment() {
    private lateinit var chemicalFromIntent: Chemical

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? = inflater.inflate(R.layout.fragment_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initImageColor()
        chemicalFromIntent = requireActivity().intent.getParcelableExtra("chemical")!!
        tv_name.text = chemicalFromIntent.materialName
        tv_location_value.text = chemicalFromIntent.locationInLab
        tv_rec_date_value.text = chemicalFromIntent.receiveDate
        tv_exp_date_value.text = chemicalFromIntent.expirationDate
        tv_lot_order_value.text = chemicalFromIntent.lotOrderNumber
        tv_bottle_count_value.text = chemicalFromIntent.bottleCount.toString()
        tv_cas_number_value.text = chemicalFromIntent.casNumber
        tv_manufacturer_value.text = chemicalFromIntent.manufacturer
        tv_type_value.text = chemicalFromIntent.type
        btn_request.setOnClickListener {
            requestPurchase()
        }
    }

    private fun requestPurchase() {
        val intentRequest = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jgellatly5@gmail.com", null)).apply {
            putExtra(Intent.EXTRA_SUBJECT, "Request Order for: " + chemicalFromIntent.materialName)
            putExtra(Intent.EXTRA_TEXT, "Please place an order for this chemical: " + chemicalFromIntent.materialName)
        }
        startActivity(Intent.createChooser(intentRequest, "Please choose an email client..."))
        requireActivity().finish()
    }

    private fun initImageColor() {
        val bundle = requireActivity().intent.extras
        val length = 4
        when (bundle?.getInt("position")?.rem(length)) {
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
        private const val TAG = "DetailFragment"
        private const val ARG_PARAM1 = "int"
        private const val ARG_PARAM2 = "title"

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
