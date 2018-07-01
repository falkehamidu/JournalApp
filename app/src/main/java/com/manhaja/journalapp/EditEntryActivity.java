package com.manhaja.journalapp;

import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class EditEntryActivity extends AppCompatActivity {
    FloatingActionButton mFABSave;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_entry);
        Toolbar myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);

//        get reference to the support actionBar

        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();

        // Enable the Up button
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
            ab.setTitle(mDate);
        }

        //        reference the FAB and set its onClick listener to launch the EntryEditorAcivity
        mFABSave = findViewById(R.id.fab);
        mFABSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(EditEntryActivity.this, "You clicked the save button", Toast.LENGTH_SHORT).show();
            }
        });

    }
    //create a date string.
    String mDate = new SimpleDateFormat("MMM dd, yyyy", Locale.getDefault()).format(new Date());

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // inflating the menu for the EditEntryActivity options from the res directory
        getMenuInflater().inflate(R.menu.menu_entry_editor, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to a click on the "Save" menu option
            case R.id.action_save:
                // Do nothing for now
                return true;
            // Respond to a click on the "Delete" menu option
            case R.id.action_delete:
                // Do nothing for now
                return true;
            // Respond to a click on the "Up" arrow button in the app bar
            case android.R.id.home:
                // Navigate back to parent activity (CatalogActivity)
                NavUtils.navigateUpFromSameTask(this);
                Toast.makeText(EditEntryActivity.this, "Alhamdulillah", Toast.LENGTH_SHORT).show();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
