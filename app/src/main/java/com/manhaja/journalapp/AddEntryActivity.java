package com.manhaja.journalapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class AddEntryActivity extends AppCompatActivity {


    private static final String TAG = "AddEntryActivity";

    FloatingActionButton mFABSave;
    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mEnrtyDatabaseReference;
    private EditText mEntryEditText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_entry);

        mFirebaseDatabase = FirebaseDatabase.getInstance();
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        mEnrtyDatabaseReference = mFirebaseDatabase.getReference().child("entries");


        mEntryEditText = findViewById(R.id.edit_entry);
        // Enable save button when there's journal entry to be saved
        mEntryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.toString().trim().length() > 0) {
                    mFABSave.setEnabled(true);

                } else {
                    mFABSave.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        get reference to the support actionBar

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date()));
        }

        //        reference the FAB and set its onClick listener to launch the EntryEditorAcivity
        mFABSave = findViewById(R.id.fab);
        mFABSave.setEnabled(false);
        mFABSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveEntry();
            }
        });

    }

    private void saveEntry() {
        JournalEntry journalEntry = new JournalEntry(mEntryEditText.getText().toString(), new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date()));
        mEnrtyDatabaseReference.push().setValue(journalEntry);
        // Clear input box
        mEntryEditText.setText("");
        NavUtils.navigateUpFromSameTask(AddEntryActivity.this);
    }
    private void cancelEntry(){
        NavUtils.navigateUpFromSameTask(this);
        Toast.makeText(AddEntryActivity.this, "The entry is discarded", Toast.LENGTH_SHORT).show();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflating the menu for the EditEntryActivity options from the res directory
        getMenuInflater().inflate(R.menu.add_entry_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Delete" menu option
            case R.id.action_cancel:
                cancelEntry();
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                cancelEntry();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
