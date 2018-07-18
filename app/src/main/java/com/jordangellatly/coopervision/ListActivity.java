package com.jordangellatly.coopervision;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity implements ChemicalAdapter.ChemicalAdapterListener {

    private static final String TAG = "ListActivity";

    private static final int REQUEST_CODE = 1;

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private RecyclerView chemicalList;
    private Toolbar tbMainSearch;
    private ChemicalAdapter adapter;
    private ProgressBar mProgressBar;

    final ArrayList<Chemicals> chemicalArrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.ListActivityTheme);
        setContentView(R.layout.activity_list);

        chemicalList = findViewById(R.id.chemical_list);
        tbMainSearch = findViewById(R.id.toolbar_search);
        mProgressBar = findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(ProgressBar.VISIBLE);
        setSupportActionBar(tbMainSearch);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

        chemicalList.setHasFixedSize(true);
        chemicalList.setLayoutManager(new LinearLayoutManager(ListActivity.this));

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chemicals chemicals = dataSnapshot.getValue(Chemicals.class);
                chemicalArrayList.add(chemicals);
                adapter = new ChemicalAdapter(chemicalArrayList, ListActivity.this);
                chemicalList.setAdapter(adapter);
                mProgressBar.setVisibility(ProgressBar.INVISIBLE);
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
                // TODO make screen for null result
                return false;
            }
        });
        return true;
    }

    @Override
    public void onChemicalSelected(Chemicals chemicals, int position) {
        Intent intent = new Intent(ListActivity.this, DetailActivity2.class);
        Bundle bundle = new Bundle();

        int chemicalIndex = chemicalArrayList.indexOf(chemicals);
        Toast.makeText(this, "itemPosition: " + String.valueOf(position) + " " + "chemicalIndex: " + String.valueOf(chemicalIndex), Toast.LENGTH_SHORT).show();
        bundle.putInt("index", chemicalIndex);
        bundle.putInt("position", position);
        bundle.putParcelable("chemical", Parcels.wrap(chemicals));
        intent.putExtras(bundle);
        startActivityForResult(intent, REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            adapter.removeAt(data.getIntExtra("position", -1));
            Toast.makeText(this, "size: " + String.valueOf(chemicalArrayList.size()), Toast.LENGTH_SHORT).show();
        }
    }
}
