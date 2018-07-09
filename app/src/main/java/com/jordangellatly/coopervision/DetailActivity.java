package com.jordangellatly.coopervision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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

    private TextView btnEdit;
    private TextView btnDelete;
    private TextView btnRequestPurchase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        tvLocation = findViewById(R.id.tv_location_value);
        tvName = findViewById(R.id.tv_name);
        tvRecDate = findViewById(R.id.tv_rec_date_value);
        tvExpDate = findViewById(R.id.tv_exp_date_value);
        tvLotOrder = findViewById(R.id.tv_lot_order_value);
        tvBottleCount = findViewById(R.id.tv_bottle_count_value);
        tvCasNumber = findViewById(R.id.tv_cas_number_value);
        tvManufacturer = findViewById(R.id.tv_manufacturer_value);
        tvType = findViewById(R.id.tv_type_value);

        btnEdit = findViewById(R.id.btn_edit);
        btnDelete = findViewById(R.id.btn_delete);
        btnRequestPurchase = findViewById(R.id.btn_request);

        Chemicals chemicalFromIntent = Parcels.unwrap(getIntent().getParcelableExtra("chemical"));
        tvName.setText(chemicalFromIntent.getMaterialName());
        tvLocation.setText(chemicalFromIntent.getLocationInLab());
        tvRecDate.setText(chemicalFromIntent.getReceiveDate());
        tvExpDate.setText(chemicalFromIntent.getExpirationDate());
        tvLotOrder.setText(chemicalFromIntent.getLotOrderNumber());
        tvBottleCount.setText(chemicalFromIntent.getBottleCount().toString());
        tvCasNumber.setText(chemicalFromIntent.getCasNumber());
        tvManufacturer.setText(chemicalFromIntent.getManufacturer());
        tvType.setText(chemicalFromIntent.getType());

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Edit this item.", Toast.LENGTH_SHORT).show();
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Delete this item.", Toast.LENGTH_SHORT).show();
            }
        });

        btnRequestPurchase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DetailActivity.this, "Request purchase order.", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
