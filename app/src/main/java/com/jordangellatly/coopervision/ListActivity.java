package com.jordangellatly.coopervision;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ListActivity extends AppCompatActivity {

    private static final String TAG = "ListActivity";

    private FirebaseDatabase database;
    private DatabaseReference myRef;

    private Button btnGetData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        btnGetData = findViewById(R.id.btn_get_data);

        database = FirebaseDatabase.getInstance();
        myRef = database.getReference();

        btnGetData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                myRef.child("masterSheet").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String item = dataSnapshot.getValue().toString();
                        Log.d(TAG, "onDataChange: " + item);
                        Toast.makeText(ListActivity.this, "Check data log", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled: " + databaseError.getMessage());
                        Toast.makeText(ListActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}
