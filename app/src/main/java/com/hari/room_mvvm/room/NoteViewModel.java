package com.hari.room_mvvm.room;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {

    private Repo noteRepo;
    private LiveData<List<NoteModel>> noteList;

    public NoteViewModel(Application application){
        super(application);
        noteRepo = new Repo(application);
        noteList = noteRepo.getNoteData();
    }

    public void insertNote(NoteModel noteModel){
        noteRepo.insertNote(noteModel);
    }

    public void updateNote(NoteModel noteModel){
        noteRepo.updateNote(noteModel);
    }

    public void deleteNote(NoteModel noteModel){
        noteRepo.deleteNote(noteModel);
    }

    public void deleteAllNotes(){
        noteRepo.deleteAlNotes();
    }

    public LiveData<List<NoteModel>> getNoteData(){
        return noteList;
    }
}
