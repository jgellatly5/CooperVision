package com.jordangellatly.coopervision;

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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private RecyclerView chemicalList;
    private Toolbar tbMainSearch;
    private ChemicalAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        chemicalList = findViewById(R.id.chemical_list);
        tbMainSearch = findViewById(R.id.toolbar_search);
        setSupportActionBar(tbMainSearch);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("chemicals");

        final ArrayList<Chemicals> chemicalArrayList = new ArrayList<>();

//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for (DataSnapshot ds: dataSnapshot.getChildren()) {
//                    Chemicals chemicals = dataSnapshot.getValue(Chemicals.class);
//                    chemicalArrayList.add(chemicals);
//                    Log.d(TAG, "onDataChange: chemicals: " + chemicalArrayList);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Chemicals chemicals = dataSnapshot.getValue(Chemicals.class);
                chemicalArrayList.add(chemicals);
                Log.d(TAG, "onChildAdded: chemicals: " + chemicals.toString());
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
        adapter = new ChemicalAdapter(chemicalArrayList);
        chemicalList.setHasFixedSize(true);
        chemicalList.setLayoutManager(new LinearLayoutManager(ListActivity.this));
        chemicalList.setAdapter(adapter);

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Toast.makeText(ListActivity.this, "You clicked on: " + names.get(i) + " and the location is: " + locations.get(i), Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(ListActivity.this, DetailActivity.class);
//                Bundle bundle = new Bundle();
//                bundle.putInt("index", i);
//                bundle.putStringArrayList("locations", locations);
//                intent.putExtras(bundle);
//                startActivity(intent);
//            }
//        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_search, menu);
        MenuItem mSearchMenuItem = menu.findItem(R.id.menu_toolbarsearch);
        SearchView searchView = (SearchView) mSearchMenuItem.getActionView();
        searchView.setQueryHint("Enter Text");
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                Log.d(TAG, "onQueryTextSubmit: query: " + s);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                Log.d(TAG, "onQueryTextChange: newText: " + s);
//                adapter.getFilter().filter(s);
                return true;
            }
        });
        Log.d(TAG, "onCreateOptionsMenu: mMenuSearchItem: " + mSearchMenuItem.getActionView());
        return true;
    }
}
