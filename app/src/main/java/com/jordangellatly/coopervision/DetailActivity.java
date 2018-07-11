package com.jordangellatly.coopervision;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

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

    private CircleImageView circleImageView;

    private TextView btnEdit;
    private TextView btnDelete;
    private TextView btnRequestPurchase;

    private Toolbar tbDetail;

    private Bundle bundle;
    private int colorChoice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDetailTheme();
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

        initToolbarColor();

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

    }

    private void initToolbarColor() {
        tbDetail = findViewById(R.id.toolbar_detail);
        setSupportActionBar(tbDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        circleImageView = findViewById(R.id.image);
        switch (colorChoice) {
            case 0:
                circleImageView.setImageResource(R.drawable.cooper_drop_orange);
                tbDetail.setBackgroundColor(Color.parseColor("#e65100"));
                break;
            case 1:
                circleImageView.setImageResource(R.drawable.cooper_drop_cyan);
                tbDetail.setBackgroundColor(Color.parseColor("#00bcd4"));
                break;
            case 2:
                circleImageView.setImageResource(R.drawable.cooper_drop_red);
                tbDetail.setBackgroundColor(Color.parseColor("#e53935"));
                break;
            case 3:
                circleImageView.setImageResource(R.drawable.cooper_drop_purple);
                tbDetail.setBackgroundColor(Color.parseColor("#7b1fa2"));
                break;
        }
    }

    private void setDetailTheme() {
        bundle = getIntent().getExtras();
        colorChoice = bundle.getInt("color");
        switch (colorChoice) {
            case 0:
                setTheme(R.style.OrangeTheme);
                break;
            case 1:
                setTheme(R.style.CyanTheme);
                break;
            case 2:
                setTheme(R.style.RedTheme);
                break;
            case 3:
                setTheme(R.style.PurpleTheme);
                break;
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            case R.id.detail_edit:
                Toast.makeText(this, "Edit this item.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_remove:
                Toast.makeText(this, "Remove this item.", Toast.LENGTH_SHORT).show();
                break;
            case R.id.detail_request:
                Toast.makeText(this, "Request purchase.", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
