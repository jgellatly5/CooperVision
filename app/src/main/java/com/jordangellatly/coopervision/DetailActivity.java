package com.jordangellatly.coopervision;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

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

    private ArrayList<String> locations;

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

//        Intent intent = getIntent();
//        Bundle extrasBundle = intent.getExtras();
//        if (!extrasBundle.isEmpty()) {
//            if (extrasBundle.containsKey("locations")) {
//                locations = extrasBundle.getStringArrayList("locations");
//            }
//            if (extrasBundle.containsKey("index")) {
//                int index = extrasBundle.getInt("index");
//                tvLocation.setText(locations.get(index));
//            }
//        }
    }
}
