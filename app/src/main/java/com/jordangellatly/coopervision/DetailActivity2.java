package com.jordangellatly.coopervision;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity2 extends AppCompatActivity {
    FragmentPagerAdapter detailPagerAdapter;

    @BindView(R.id.toolbar_detail)
    Toolbar tbDetail;

    private Bundle bundle;
    private int colorChoice;
    private int chemicalIndex;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setDetailTheme();
        setContentView(R.layout.activity_detail2);
        ButterKnife.bind(this);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

        initToolbarColor();

        ViewPager viewPager = findViewById(R.id.view_pager);
        detailPagerAdapter = new DetailPagerAdapter(getSupportFragmentManager(), DetailActivity2.this);
        viewPager.setAdapter(detailPagerAdapter);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
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
