package com.jordangellatly.coopervision.search;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.jordangellatly.coopervision.details.CreateActivity;
import com.jordangellatly.coopervision.details.DetailActivity;
import com.jordangellatly.coopervision.R;
import com.jordangellatly.coopervision.models.Chemicals;

import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ListActivity extends AppCompatActivity implements ChemicalAdapter.ChemicalAdapterListener {

    private static final String TAG = "ListActivity";

    private static final int REQUEST_CODE = 1;

    @BindView(R.id.toolbar_search)
    Toolbar toolbarSearch;
    @BindView(R.id.chemical_list)
    RecyclerView chemicalList;
    @BindView(R.id.progress_bar)
    ProgressBar progressBar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.tv_empty_list)
    TextView tvEmptyList;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ChemicalAdapter adapter;

    final ArrayList<Chemicals> chemicalArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        ButterKnife.bind(this);

        initWidgets();

        fetchListData();
    }

    private void initWidgets() {
        setSupportActionBar(toolbarSearch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("");
        tvEmptyList.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(ProgressBar.VISIBLE);
    }

    private void fetchListData() {
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

        String filter = getIntent().getStringExtra("filter");
        if (filter != null) {
            Query filterData = myRef.orderByChild("locationInLab").equalTo(filter);
            filterData.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    getFirebaseData(dataSnapshot);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        } else {
            myRef.addChildEventListener(new ChildEventListener() {
                @Override
                public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                    getFirebaseData(dataSnapshot);
                }

                @Override
                public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                }

                @Override
                public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    @OnClick(R.id.fab)
    public void addChemical() {
        Intent intent = new Intent(ListActivity.this, CreateActivity.class);
        Bundle bundle = new Bundle();
        Chemicals chemicals = chemicalArrayList.get(chemicalArrayList.size() - 1);
        bundle.putParcelable("lastChemical", Parcels.wrap(chemicals));
        intent.putExtras(bundle);
        startActivity(intent);
    }

    private void getFirebaseData(@NonNull DataSnapshot dataSnapshot) {
        Chemicals chemicals = dataSnapshot.getValue(Chemicals.class);
        chemicalArrayList.add(chemicals);
        adapter = new ChemicalAdapter(chemicalArrayList, ListActivity.this);
        chemicalList.setAdapter(adapter);
        chemicalList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        progressBar.setVisibility(ProgressBar.INVISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.menu_toolbarsearch);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setQueryHint("Enter Chemical Name");
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                adapter.getFilter().filter(s);
                if (adapter.getItemCount() == 0) {
                    tvEmptyList.setVisibility(View.VISIBLE);
                } else {
                    tvEmptyList.setVisibility(View.INVISIBLE);
                }
                return false;
            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
            default:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onChemicalSelected(Chemicals chemicals, int position) {
        Intent intent = new Intent(ListActivity.this, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putParcelable("chemical", Parcels.wrap(chemicals));
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            int position = data.getIntExtra("position", -1);
            String stringExtra = data.getStringExtra("intent");
            if (stringExtra.equals("remove")) {
                adapter.removeAt(position);
            }
            if (stringExtra.equals("update")) {
                progressBar.setVisibility(ProgressBar.VISIBLE);
                adapter.update();
                fetchListData();
            }
        }
    }
}
