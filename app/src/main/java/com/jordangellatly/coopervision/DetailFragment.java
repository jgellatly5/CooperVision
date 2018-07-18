package com.jordangellatly.coopervision;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailFragment extends Fragment {

    private static final String TAG = "DetailFragment";

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

    private Unbinder unbinder;

    private static final String ARG_PARAM1 = "int";
    private static final String ARG_PARAM2 = "title";

    private String title;
    private int page;

    public DetailFragment() {

    }

    public static DetailFragment newInstance(String title, int page) {
        DetailFragment fragment = new DetailFragment();
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
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initImageColor();
        Chemicals chemicalFromIntent = Parcels.unwrap(getActivity().getIntent().getParcelableExtra("chemical"));
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

    private void initImageColor() {
        Bundle bundle = getActivity().getIntent().getExtras();
        int length = 4;
        int colorChoice = bundle.getInt("position") % length;
        switch (colorChoice) {
            case 0:
                circleImageView.setImageResource(R.drawable.cooper_drop_orange);
                break;
            case 1:
                circleImageView.setImageResource(R.drawable.cooper_drop_cyan);
                break;
            case 2:
                circleImageView.setImageResource(R.drawable.cooper_drop_red);
                break;
            case 3:
                circleImageView.setImageResource(R.drawable.cooper_drop_purple);
                break;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
