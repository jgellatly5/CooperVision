package com.jordangellatly.coopervision;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ChemicalAdapter extends RecyclerView.Adapter<ChemicalAdapter.ViewHolder> {

    private static final String TAG = "ChemicalAdapter";

    private List<Chemicals> mChemicals;

    public ChemicalAdapter(List<Chemicals> chemicals) {
        this.mChemicals = chemicals;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mChemicalName;
        public ImageView mImage;

        public ViewHolder(View itemView) {
            super(itemView);

            mChemicalName = itemView.findViewById(R.id.chemical_name);
            mImage = itemView.findViewById(R.id.logo);
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
        Chemicals chemicals = mChemicals.get(position);
        Log.d(TAG, "onBindViewHolder: " + chemicals.toString());

        if (position % 2 == 0) {
            holder.mImage.setImageResource(R.drawable.cooper_drop_cyan);
        }
        if (position % 3 == 0) {
            holder.mImage.setImageResource(R.drawable.cooper_drop_red);
        }

        TextView chemicalName = holder.mChemicalName;
        chemicalName.setText(chemicals.getMaterialName());
    }

    @Override
    public int getItemCount() {
        return mChemicals.size();
    }
}
