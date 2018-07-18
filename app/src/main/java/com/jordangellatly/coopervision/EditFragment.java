package com.jordangellatly.coopervision;

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

import org.parceler.Parcels;

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
        Chemicals chemicalFromIntent = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("chemical"));
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
        Bundle bundle = getActivity().getIntent().getExtras();
        int length = 4;
        int colorChoice = bundle.getInt("position") % length;
        switch (colorChoice) {
            case 0:
                circleImageView.setImageResource(R.drawable.cooper_drop_orange);
                btnUpdate.setBackgroundColor(Color.parseColor("#e65100"));
                break;
            case 1:
                circleImageView.setImageResource(R.drawable.cooper_drop_cyan);
                btnUpdate.setBackgroundColor(Color.parseColor("#00bcd4"));
                break;
            case 2:
                circleImageView.setImageResource(R.drawable.cooper_drop_red);
                btnUpdate.setBackgroundColor(Color.parseColor("#e53935"));
                break;
            case 3:
                circleImageView.setImageResource(R.drawable.cooper_drop_purple);
                btnUpdate.setBackgroundColor(Color.parseColor("#7b1fa2"));
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
        Toast.makeText(getActivity(), "Update", Toast.LENGTH_SHORT).show();
        getActivity().finish();
    }
}
