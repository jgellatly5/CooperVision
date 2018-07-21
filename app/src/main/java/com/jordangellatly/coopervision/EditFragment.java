package com.jordangellatly.coopervision;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.parceler.Parcels;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class EditFragment extends Fragment {

    private static final String TAG = "EditFragment";

    @BindView(R.id.et_name)
    EditText etName;
    @BindView(R.id.image)
    CircleImageView circleImageView;
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

    private static final String ARG_PARAM1 = "int";
    private static final String ARG_PARAM2 = "title";

    private Unbinder unbinder;

    private String title;
    private int page;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private Chemicals chemicalFromIntent;
    private Bundle bundleFromListActivity;

    public EditFragment() {

    }

    public static EditFragment newInstance(String title, int page) {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, page);
        args.putString(ARG_PARAM2, title);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PARAM1, 0);
        title = getArguments().getString(ARG_PARAM2);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");
        chemicalFromIntent = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("chemical"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageColor();

        etName.setText(chemicalFromIntent.getMaterialName());
        etLocationValue.setText(chemicalFromIntent.getLocationInLab());
        etRecDateValue.setText(chemicalFromIntent.getReceiveDate());
        etExpDateValue.setText(chemicalFromIntent.getExpirationDate());
        etLotOrderValue.setText(chemicalFromIntent.getLotOrderNumber());
        etBottleCountValue.setText(chemicalFromIntent.getBottleCount().toString());
        etCasNumberValue.setText(chemicalFromIntent.getCasNumber());
        etManufacturerValue.setText(chemicalFromIntent.getManufacturer());
        etTypeValue.setText(chemicalFromIntent.getType());
    }

    private void initImageColor() {
        bundleFromListActivity = getActivity().getIntent().getExtras();
        int length = 4;
        int colorChoice = bundleFromListActivity.getInt("position") % length;
        switch (colorChoice) {
            case 0:
                circleImageView.setImageResource(R.drawable.cooper_drop_orange);
                btnUpdate.setBackgroundResource(R.drawable.button_orange);
                break;
            case 1:
                circleImageView.setImageResource(R.drawable.cooper_drop_cyan);
                btnUpdate.setBackgroundResource(R.drawable.button_cyan);
                break;
            case 2:
                circleImageView.setImageResource(R.drawable.cooper_drop_red);
                btnUpdate.setBackgroundResource(R.drawable.button_red);
                break;
            case 3:
                circleImageView.setImageResource(R.drawable.cooper_drop_purple);
                btnUpdate.setBackgroundResource(R.drawable.button_purple);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.btn_update)
    public void update() {
        Map<String, Object> chemicalUpdates = new HashMap<>();
        chemicalUpdates.put("bottleCount", Long.parseLong(etBottleCountValue.getText().toString()));
        chemicalUpdates.put("casNumber", etCasNumberValue.getText().toString());
        chemicalUpdates.put("expirationDate", etExpDateValue.getText().toString());
        chemicalUpdates.put("locationInLab", etLocationValue.getText().toString());
        chemicalUpdates.put("lotOrderNumber", etLotOrderValue.getText().toString());
        chemicalUpdates.put("manufacturer", etManufacturerValue.getText().toString());
        chemicalUpdates.put("materialName", etName.getText().toString());
        chemicalUpdates.put("receiveDate", etRecDateValue.getText().toString());
        chemicalUpdates.put("type", etTypeValue.getText().toString());
        myRef.child(String.valueOf(chemicalFromIntent.getId())).updateChildren(chemicalUpdates);

        Intent backToListIntent = new Intent();
        bundleFromListActivity.putString("intent", "update");
        backToListIntent.putExtras(bundleFromListActivity);
        getActivity().setResult(Activity.RESULT_OK, backToListIntent);
        getActivity().finish();
    }
}
