package com.example.jardosh.notekeeping;

import android.Manifest;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;

public class NoteEditor extends AppCompatActivity {

    static int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_editor);

        EditText editText = (EditText) findViewById(R.id.editText);

        Intent intent = getIntent();
        noteId = intent.getIntExtra("noteId",-1);
        Log.i("Note ID",String.valueOf(noteId));

        if(noteId != -1) {
            editText.setText(MainActivity.notes.get(noteId));
        } else {
            MainActivity.notes.add("");
            noteId = MainActivity.notes.size()-1;
        }

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                MainActivity.notes.set(noteId,String.valueOf(s));
                MainActivity.arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
