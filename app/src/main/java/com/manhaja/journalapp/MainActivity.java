package com.manhaja.journalapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton mActionButton;


//    Firebase instance variables
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEnrtyDatabaseReference;
    private ChildEventListener mChildEventListener;

    private static final String TAG = "MainActivity";

    public static final String ANONYMOUS = "anonymous";
    public static final int DEFAULT_MSG_LENGTH_LIMIT = 1000;

    private ListView mEntryListView;
    private EntryAdapter mEntryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEntryListView = findViewById(R.id.entryListView);
        // Initialize message ListView and its adapter
        List<JournalEntry> journalEntries = new ArrayList<>();
        mEntryAdapter = new EntryAdapter(this, R.layout.item_entry, journalEntries);
        mEntryListView.setAdapter(mEntryAdapter);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mFirebaseDatabase.setPersistenceEnabled(true);
        mEnrtyDatabaseReference = mFirebaseDatabase.getReference().child("entries");

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        reference the FAB and set its onClick listener to launch the EntryEditorAcivity
        mActionButton = findViewById(R.id.fab);
        mActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddEntryActivity.class);
                startActivity(intent);
            }
        });

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                JournalEntry journalEntry = dataSnapshot.getValue(JournalEntry.class);
                mEntryAdapter.add(journalEntry);
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
        };
        mEnrtyDatabaseReference.addChildEventListener(mChildEventListener);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflating the menu options from the res directory
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.settings:
                // Do nothing for now
                return true;
            case R.id.action_delete_all_entries:
                // Do nothing for now
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
