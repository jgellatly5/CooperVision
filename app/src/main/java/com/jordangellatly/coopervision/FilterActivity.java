package com.jordangellatly.coopervision;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

public class FilterActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @BindView(R.id.filter_image)
    CircleImageView filterImage;
    @BindView(R.id.title_filter)
    TextView titleFilter;
    @BindView(R.id.location_selection)
    Spinner locationSelection;
    @BindView(R.id.btn_search)
    Button btnSearch;
    @BindView(R.id.btn_search_all)
    Button btnSearchAll;

    private Intent intentWithFilter;
    private Intent intentSearchAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        ButterKnife.bind(this);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.locations_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSelection.setAdapter(adapter);
        locationSelection.setOnItemSelectedListener(this);

        intentWithFilter = new Intent(FilterActivity.this, ListActivity.class);
        intentSearchAll = new Intent(FilterActivity.this, ListActivity.class);
    }

    @OnClick(R.id.btn_search)
    public void search() {
        startActivity(intentWithFilter);
    }

    @OnClick({R.id.btn_search_all})
    public void searchAll() {
        startActivity(intentSearchAll);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selection = adapterView.getItemAtPosition(i).toString();
        intentWithFilter.putExtra("filter", selection);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
