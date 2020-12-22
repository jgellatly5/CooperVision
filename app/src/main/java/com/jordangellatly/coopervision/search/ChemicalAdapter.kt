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
import com.jordangellatly.coopervision.models.Chemical
import java.util.*

class ChemicalAdapter(
        private val mChemicals: MutableList<Chemical>,
        private val listener: ChemicalAdapterListener
) : RecyclerView.Adapter<ChemicalAdapter.ViewHolder>(), Filterable {
    private var mChemicalsFiltered: MutableList<Chemical>

    init {
        mChemicalsFiltered = mChemicals
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mChemicalName: TextView = itemView.findViewById(R.id.chemical_name)
        val mImage: ImageView = itemView.findViewById(R.id.logo)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                listener.onChemicalSelected(mChemicalsFiltered[position], position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChemicalAdapter.ViewHolder {
        val chemicalView =
                LayoutInflater.from(parent.context).inflate(R.layout.item_chemical, parent, false)
        return ViewHolder(chemicalView)
    }

    override fun onBindViewHolder(holder: ChemicalAdapter.ViewHolder, position: Int) {
        val chemical = mChemicalsFiltered[position]
        when (position % COLOR_SELECTIONS) {
            0 -> holder.mImage.setImageResource(R.drawable.cooper_drop_orange)
            1 -> holder.mImage.setImageResource(R.drawable.cooper_drop_cyan)
            2 -> holder.mImage.setImageResource(R.drawable.cooper_drop_red)
            3 -> holder.mImage.setImageResource(R.drawable.cooper_drop_purple)
        }
        val chemicalName = holder.mChemicalName
        chemicalName.text = chemical.materialName
    }

    override fun getItemCount(): Int = mChemicalsFiltered.size

    override fun getFilter(): Filter = object : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            mChemicalsFiltered = if (charSequence.isEmpty()) {
                mChemicals
            } else {
                val filteredList = ArrayList<Chemical>()
                for (row in mChemicals) {
                    if (row.materialName.toLowerCase(Locale.ROOT).startsWith(charSequence)) {
                        filteredList.add(row)
                    }
                }
                filteredList
            }
            val filterResults = FilterResults()
            filterResults.values = mChemicalsFiltered
            return filterResults
        }

        override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
            mChemicalsFiltered = filterResults.values as ArrayList<Chemical>
            notifyDataSetChanged()
        }
    }

    interface ChemicalAdapterListener {
        fun onChemicalSelected(chemical: Chemical, position: Int)
    }

    fun removeAt(position: Int) {
        mChemicalsFiltered.removeAt(position)
        notifyItemRemoved(position)
        notifyItemRangeChanged(position, mChemicalsFiltered.size)
    }

    fun update() {
        mChemicalsFiltered.clear()
        notifyDataSetChanged()
    }

    companion object {
        private const val COLOR_SELECTIONS = 4
    }
}
