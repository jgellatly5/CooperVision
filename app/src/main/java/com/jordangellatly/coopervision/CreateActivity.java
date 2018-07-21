package com.jordangellatly.coopervision;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

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
    @BindView(R.id.btn_create)
    Button btnCreate;

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

        btnCreate.setBackgroundResource(R.drawable.button_orange);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");
    }

    @OnClick(R.id.btn_create)
    public void onViewClicked() {
        bottleCount = Long.parseLong(etBottleCountValue.getText().toString());
        casNumber = etCasNumberValue.getText().toString();
        expirationDate = etExpDateValue.getText().toString();
        locationInLab = etLocationValue.getText().toString();
        lotOrderNumber = etLotOrderValue.getText().toString();
        manufacturer = etManufacturerValue.getText().toString();
        materialName = etName.getText().toString();
        receiveDate = etRecDateValue.getText().toString();
        type = etTypeValue.getText().toString();
        writeNewChemical(bottleCount, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type);
        finish();
    }

    private void writeNewChemical(Long bottleCount, String casNumber, String expirationDate, String locationInLab, String lotOrderNumber, String manufacturer, String materialName, String receiveDate, String type) {
        Chemicals lastChemical = Parcels.unwrap(getIntent().getParcelableExtra("lastChemical"));
        long id = lastChemical.getId() + 1;
        Chemicals chemical = new Chemicals(bottleCount, id, casNumber, expirationDate, locationInLab, lotOrderNumber, manufacturer, materialName, receiveDate, type);
        myRef.child(String.valueOf(chemical.getId())).setValue(chemical);
    }
}
