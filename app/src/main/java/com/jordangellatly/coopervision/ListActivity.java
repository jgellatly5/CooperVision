package com.jordangellatly.coopervision;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.list_view);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("masterSheet");

        final ArrayList<String> chemicals = new ArrayList<>();

        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String chemical = ds.getValue().toString();
                    if (ds.getKey().equals("8")) {
                        Log.d(TAG, "onChildAdded: " + chemical);
                        chemicals.add(chemical);
                    }
                }
                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(ListActivity.this, android.R.layout.simple_list_item_1, chemicals);
                listView.setAdapter(itemsAdapter);

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
