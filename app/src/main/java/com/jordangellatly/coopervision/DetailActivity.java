package com.jordangellatly.coopervision;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.NavUtils;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

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
    private int chemicalIndex;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChemicalAdapter adapter;

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

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

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
        int length = 4;
        colorChoice = bundle.getInt("position") % length;
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
                chemicalIndex = bundle.getInt("index") + 1;
                myRef.child(String.valueOf(chemicalIndex)).removeValue();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("position", bundle.getInt("position"));
                setResult(Activity.RESULT_OK, returnIntent);
                Toast.makeText(this, String.valueOf(chemicalIndex), Toast.LENGTH_SHORT).show();
                finish();
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
