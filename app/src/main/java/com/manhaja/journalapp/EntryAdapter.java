package com.manhaja.journalapp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class EntryAdapter extends ArrayAdapter<JournalEntry> {

    public EntryAdapter(Context context, int resource, List<JournalEntry> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null){
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_entry, parent, false);
        }
        TextView dateTextView =  convertView.findViewById(R.id.tv_date_of_entry);
        TextView entryTextView =  convertView.findViewById(R.id.tv_entry_view);

        JournalEntry entry = getItem(position);

        dateTextView.setText(entry.getmDate());
        entryTextView.setText(entry.getmEntry());
        return convertView;
    }
}
