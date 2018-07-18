package com.jordangellatly.coopervision;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    private static final String TAG = "DetailActivity";

    @BindView(R.id.toolbar_detail)
    Toolbar tbDetail;
    @BindView(R.id.image)
    CircleImageView circleImageView;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_location)
    TextView tvLocation;
    @BindView(R.id.tv_location_value)
    TextView tvLocationValue;
    @BindView(R.id.tv_rec_date)
    TextView tvRecDate;
    @BindView(R.id.tv_rec_date_value)
    TextView tvRecDateValue;
    @BindView(R.id.tv_exp_date)
    TextView tvExpDate;
    @BindView(R.id.tv_exp_date_value)
    TextView tvExpDateValue;
    @BindView(R.id.tv_lot_order)
    TextView tvLotOrder;
    @BindView(R.id.tv_lot_order_value)
    TextView tvLotOrderValue;
    @BindView(R.id.tv_bottle_count)
    TextView tvBottleCount;
    @BindView(R.id.tv_bottle_count_value)
    TextView tvBottleCountValue;
    @BindView(R.id.tv_cas_number)
    TextView tvCasNumber;
    @BindView(R.id.tv_cas_number_value)
    TextView tvCasNumberValue;
    @BindView(R.id.tv_manufacturer)
    TextView tvManufacturer;
    @BindView(R.id.tv_manufacturer_value)
    TextView tvManufacturerValue;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.tv_type_value)
    TextView tvTypeValue;
    @BindView(R.id.table)
    TableLayout table;

    private TextView btnEdit;
    private TextView btnDelete;
    private TextView btnRequestPurchase;

    private Bundle bundle;
    private int colorChoice;
    private int chemicalIndex;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDetailTheme();
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

        initToolbarColor();

        Chemicals chemicalFromIntent = Parcels.unwrap(getIntent().getParcelableExtra("chemical"));
        tvName.setText(chemicalFromIntent.getMaterialName());
        tvLocationValue.setText(chemicalFromIntent.getLocationInLab());
        tvRecDateValue.setText(chemicalFromIntent.getReceiveDate());
        tvExpDateValue.setText(chemicalFromIntent.getExpirationDate());
        tvLotOrderValue.setText(chemicalFromIntent.getLotOrderNumber());
        tvBottleCountValue.setText(chemicalFromIntent.getBottleCount().toString());
        tvCasNumberValue.setText(chemicalFromIntent.getCasNumber());
        tvManufacturerValue.setText(chemicalFromIntent.getManufacturer());
        tvTypeValue.setText(chemicalFromIntent.getType());
    }

    private void initToolbarColor() {
        setSupportActionBar(tbDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
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
