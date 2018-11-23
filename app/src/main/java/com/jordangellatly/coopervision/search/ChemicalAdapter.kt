package com.jordangellatly.coopervision.search

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import com.jordangellatly.coopervision.R
import com.jordangellatly.coopervision.models.Chemicals
import java.util.*

class ChemicalAdapter(private val mChemicals: MutableList<Chemicals>, private val listener: ChemicalAdapterListener) : RecyclerView.Adapter<ChemicalAdapter.ViewHolder>(), Filterable {
    private val TAG = "ChemicalAdapter"
    var colorChoice: Int = 0
    private var mChemicalsFiltered: MutableList<Chemicals>? = null

    init {
        this.mChemicalsFiltered = mChemicals
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): Filter.FilterResults {
                val charString = charSequence.toString()
                if (charString.isEmpty()) {
                    mChemicalsFiltered = mChemicals
                } else {
                    val filteredList = ArrayList<Chemicals>()
                    for (row in mChemicals) {
                        if (row.materialName!!.toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    mChemicalsFiltered = filteredList
                }
                val filterResults = Filter.FilterResults()
                filterResults.values = mChemicalsFiltered
                return filterResults
            }

            override fun publishResults(charSequence: CharSequence, filterResults: Filter.FilterResults) {
                mChemicalsFiltered = filterResults.values as ArrayList<Chemicals>
                notifyDataSetChanged()
            }
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val mChemicalName: TextView
        val mImage: ImageView

        init {

            mChemicalName = itemView.findViewById(R.id.chemical_name)
            mImage = itemView.findViewById(R.id.logo)
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onChemicalSelected(mChemicalsFiltered!![position], position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChemicalAdapter.ViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val chemicalView = inflater.inflate(R.layout.item_chemical, parent, false)
        return ViewHolder(chemicalView)
    }

    override fun onBindViewHolder(holder: ChemicalAdapter.ViewHolder, position: Int) {
        val chemicals = mChemicalsFiltered!![position]

        val length = 4
        colorChoice = position % length
        when (colorChoice) {
            0 -> holder.mImage.setImageResource(R.drawable.cooper_drop_orange)
            1 -> holder.mImage.setImageResource(R.drawable.cooper_drop_cyan)
            2 -> holder.mImage.setImageResource(R.drawable.cooper_drop_red)
            3 -> holder.mImage.setImageResource(R.drawable.cooper_drop_purple)
        }
        val chemicalName = holder.mChemicalName
        chemicalName.text = chemicals.materialName
    }

    override fun getItemCount(): Int {
        return mChemicalsFiltered!!.size
    }

    interface ChemicalAdapterListener {
        fun onChemicalSelected(chemicals: Chemicals, position: Int)
    }

    fun removeAt(position: Int) {
        mChemicalsFiltered!!.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mChemicalsFiltered!!.size)
    }

    fun update() {
        mChemicalsFiltered!!.clear()
        notifyDataSetChanged()
    }
}
