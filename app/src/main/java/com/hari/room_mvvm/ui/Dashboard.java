package com.hari.room_mvvm.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.hari.room_mvvm.R;
import com.hari.room_mvvm.adapter.NotesAdapter;
import com.hari.room_mvvm.room.NoteModel;
import com.hari.room_mvvm.room.NoteViewModel;

import java.util.List;

public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        RecyclerView notesRecycler = findViewById(R.id.note_recycler);
        notesRecycler.setLayoutManager(new GridLayoutManager(this, 2));

        NotesAdapter notesAdapter = new NotesAdapter(this);
        notesRecycler.setAdapter(notesAdapter);

        NoteViewModel noteViewModel = ViewModelProviders.of(this).get(NoteViewModel.class);
        noteViewModel.getNoteData().observe(this, notesAdapter::setNotes);

        FloatingActionButton addNoteFab = findViewById(R.id.add_note_fab_dashboard);
        addNoteFab.setOnClickListener(v->{

            Dialog dialog = new Dialog(this);
            dialog.setContentView(R.layout.dialog_add_note);
            dialog.setCanceledOnTouchOutside(true);
            TextInputLayout titleTIL, messageTIL;
            TextInputEditText titleTIET, messageTIET;
            Button addBtn;
            titleTIL = dialog.findViewById(R.id.title_til_add_note_dialog);
            messageTIL = dialog.findViewById(R.id.message_til_add_note_dialog);
            titleTIET = dialog.findViewById(R.id.title_tiet_add_note_dialog);
            messageTIET = dialog.findViewById(R.id.message_tiet_add_note_dialog);
            addBtn = dialog.findViewById(R.id.add_btn_add_note_dialog);

            titleTIET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if (!charSequence.toString().trim().equals("")){

                        titleTIL.setErrorEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            messageTIET.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    if (!charSequence.toString().trim().equals("")){

                        messageTIL.setErrorEnabled(false);
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

            addBtn.setOnClickListener(v1->{

                if (!titleTIET.getText().toString().equals("") && !messageTIET.getText().toString().equals("")){

                noteViewModel.insertNote(new NoteModel(titleTIET.getText().toString(), messageTIET.getText().toString()));
                dialog.dismiss();
                }else {

                    if (titleTIET.getText().toString().trim().equals("")){

                        titleTIL.setError("Title required");
                        titleTIL.setErrorIconDrawable(0);
                    }else if (messageTIET.getText().toString().trim().equals("")){

                        messageTIL.setError("Message required");
                        messageTIL.setErrorIconDrawable(0);
                    }
                }
            });

            dialog.show();
            Window window = dialog.getWindow();
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        });
    }
}