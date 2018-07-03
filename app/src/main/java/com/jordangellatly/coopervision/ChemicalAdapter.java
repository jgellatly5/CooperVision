package com.jordangellatly.coopervision;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class ChemicalAdapter extends RecyclerView.Adapter<ChemicalAdapter.ViewHolder> {

    private List<Chemical> mChemicals;

    public ChemicalAdapter(List<Chemical> chemicals) {
        this.mChemicals = chemicals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mChemicalName;
        public TextView mChemicalLocation;

        public ViewHolder(View itemView) {
            super(itemView);

            mChemicalName = itemView.findViewById(R.id.chemical_name);
            mChemicalLocation = itemView.findViewWithTag(R.id.chemical_location);
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
        Chemical chemical = mChemicals.get(position);

        TextView chemicalName = holder.mChemicalName;
        chemicalName.setText(chemical.getMaterialName());
    }

    @Override
    public int getItemCount() {
        return mChemicals.size();
    }
}
