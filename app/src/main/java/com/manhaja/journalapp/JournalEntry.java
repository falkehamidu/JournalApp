package com.manhaja.journalapp;

public class JournalEntry {
    private String mEntry;
    private String mDate;

    public JournalEntry() {
    }

    public JournalEntry(String mEntry, String mDate) {
        this.mEntry = mEntry;
        this.mDate = mDate;
    }

    public String getmEntry() {
        return mEntry;
    }

    public String getmDate() {
        return mDate;
    }

    public void setmEntry(String mEntry) {
        this.mEntry = mEntry;
    }

    public void setmDate(String mDate) {
        this.mDate = mDate;
    }
}
