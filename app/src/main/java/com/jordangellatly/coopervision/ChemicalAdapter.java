package com.jordangellatly.coopervision;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ChemicalAdapter extends RecyclerView.Adapter<ChemicalAdapter.ViewHolder> implements Filterable {

    private static final String TAG = "ChemicalAdapter";
    public int colorChoice;

    private List<Chemicals> mChemicals;
    private List<Chemicals> mChemicalsFiltered;
    private ChemicalAdapterListener listener;

    public ChemicalAdapter(List<Chemicals> mChemicals, ChemicalAdapterListener listener) {
        this.mChemicals = mChemicals;
        this.mChemicalsFiltered = mChemicals;
        this.listener = listener;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mChemicalsFiltered = mChemicals;
                } else {
                    List<Chemicals> filteredList = new ArrayList<>();
                    for (Chemicals row : mChemicals) {
                        if (row.getMaterialName().toLowerCase().startsWith(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    mChemicalsFiltered = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mChemicalsFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mChemicalsFiltered = (ArrayList<Chemicals>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mChemicalName;
        private ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mChemicalName = itemView.findViewById(R.id.chemical_name);
            mImage = itemView.findViewById(R.id.logo);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    listener.onChemicalSelected(mChemicalsFiltered.get(position), position);
                }
            });
        }
    }

    @NonNull
    @Override
    public ChemicalAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View chemicalView = inflater.inflate(R.layout.item_chemical, parent, false);

        ViewHolder viewHolder = new ViewHolder(chemicalView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChemicalAdapter.ViewHolder holder, int position) {
        Chemicals chemicals = mChemicalsFiltered.get(position);

        int length = 4;
        colorChoice = position % length;
        switch (colorChoice) {
            case 0:
                holder.mImage.setImageResource(R.drawable.cooper_drop_orange);
                break;
            case 1:
                holder.mImage.setImageResource(R.drawable.cooper_drop_cyan);
                break;
            case 2:
                holder.mImage.setImageResource(R.drawable.cooper_drop_red);
                break;
            case 3:
                holder.mImage.setImageResource(R.drawable.cooper_drop_purple);
                break;
        }
        TextView chemicalName = holder.mChemicalName;
        chemicalName.setText(chemicals.getMaterialName());
    }

    @Override
    public int getItemCount() {
        return mChemicalsFiltered.size();
    }

    public interface ChemicalAdapterListener {
        void onChemicalSelected(Chemicals chemicals, int position);
    }

    public void removeAt(int position) {
        mChemicalsFiltered.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, mChemicalsFiltered.size());
    }
}
