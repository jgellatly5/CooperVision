package com.jordangellatly.coopervision.details;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.jordangellatly.coopervision.R;
import com.jordangellatly.coopervision.models.Chemicals;

import org.parceler.Parcels;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {
    FragmentPagerAdapter detailPagerAdapter;

    @BindView(R.id.toolbar_detail)
    Toolbar tbDetail;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private Bundle bundle;
    private int colorChoice;

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

        detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), DetailActivity.this);
        viewPager.setAdapter(detailPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbarColor() {
        setSupportActionBar(tbDetail);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        switch (colorChoice) {
            case 0:
                tbDetail.setBackgroundColor(Color.parseColor("#e65100"));
                break;
            case 1:
                tbDetail.setBackgroundColor(Color.parseColor("#00bcd4"));
                break;
            case 2:
                tbDetail.setBackgroundColor(Color.parseColor("#e53935"));
                break;
            case 3:
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
            case R.id.detail_remove:
                createDialog();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailActivity.this);
        builder.setMessage("Are you sure you would like to remove this chemical from the inventory?")
                .setTitle("Warning")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        removeChemical();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(DetailActivity.this, "Cancel", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void removeChemical() {
        Chemicals chemical = Parcels.unwrap(getIntent().getParcelableExtra("chemical"));
        myRef.child(String.valueOf(chemical.getId())).removeValue();
        Intent returnIntent = new Intent();
        bundle.putString("intent", "remove");
        returnIntent.putExtras(bundle);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return super.onCreateOptionsMenu(menu);
    }
}
