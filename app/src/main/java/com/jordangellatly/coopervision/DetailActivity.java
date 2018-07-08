package com.jordangellatly.coopervision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.parceler.Parcels;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private TextView tvLocation;
    private TextView tvName;
    private TextView tvRecDate;
    private TextView tvExpDate;
    private TextView tvLotOrder;
    private TextView tvBottleCount;
    private TextView tvCasNumber;
    private TextView tvManufacturer;
    private TextView tvType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvLocation = findViewById(R.id.tv_location);
        tvName = findViewById(R.id.tv_name);
        tvRecDate = findViewById(R.id.tv_rec_date);
        tvExpDate = findViewById(R.id.tv_exp_date);
        tvLotOrder = findViewById(R.id.tv_lot_order);
        tvBottleCount = findViewById(R.id.tv_bottle_count);
        tvCasNumber = findViewById(R.id.tv_cas_number);
        tvManufacturer = findViewById(R.id.tv_manufacturer);
        tvType = findViewById(R.id.tv_type);

        Chemicals chemicalFromIntent = Parcels.unwrap(getIntent().getParcelableExtra("chemical"));
        tvName.setText(getResources().getString(R.string.chemical_name) + chemicalFromIntent.getMaterialName());
        tvLocation.setText(getResources().getString(R.string.chemical_location) + chemicalFromIntent.getLocationInLab());
        tvRecDate.setText(getResources().getString(R.string.received_date) + chemicalFromIntent.getReceiveDate());
        tvExpDate.setText(getResources().getString(R.string.exp_date) + chemicalFromIntent.getExpirationDate());
        tvLotOrder.setText(getResources().getString(R.string.lot_order_number) + chemicalFromIntent.getLotOrderNumber());
        tvBottleCount.setText(getResources().getString(R.string.bottle_count) + chemicalFromIntent.getBottleCount().toString());
        tvCasNumber.setText(getResources().getString(R.string.cas_number) + chemicalFromIntent.getCasNumber());
        tvManufacturer.setText(getResources().getString(R.string.manufacturer) + chemicalFromIntent.getManufacturer());
        tvType.setText(getResources().getString(R.string.type) + chemicalFromIntent.getType());
    }
}
