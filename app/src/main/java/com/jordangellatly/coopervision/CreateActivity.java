package com.jordangellatly.coopervision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class CreateActivity extends AppCompatActivity {

    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.et_location_value)
    EditText etLocationValue;
    @BindView(R.id.et_rec_date_value)
    EditText etRecDateValue;
    @BindView(R.id.et_exp_date_value)
    EditText etExpDateValue;
    @BindView(R.id.et_lot_order_value)
    EditText etLotOrderValue;
    @BindView(R.id.et_bottle_count_value)
    EditText etBottleCountValue;
    @BindView(R.id.et_cas_number_value)
    EditText etCasNumberValue;
    @BindView(R.id.et_manufacturer_value)
    EditText etManufacturerValue;
    @BindView(R.id.et_type_value)
    EditText etTypeValue;
    @BindView(R.id.btn_update)
    Button btnUpdate;

    private Long bottleCount;
    private String casNumber;
    private String expirationDate;
    private String locationInLab;
    private String lotOrderNumber;
    private String manufacturer;
    private String materialName;
    private String receiveDate;
    private String type;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        ButterKnife.bind(this);

        btnUpdate.setBackgroundResource(R.drawable.button_orange);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");
    }

    @OnClick(R.id.btn_update)
    public void onViewClicked() {
        Toast.makeText(this, "Adding chemical to inventory.", Toast.LENGTH_SHORT).show();
        finish();
    }

//    private void writeNewChemical(Long bottleCount, String casNumber, String expirationDate, String locationInLab, String lotOrderNumber, String manufacturer, String materialName, String receiveDate, String type) {
//        Chemicals chemical = new Chemicals(bottleCount, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type);
//
//    }
}
