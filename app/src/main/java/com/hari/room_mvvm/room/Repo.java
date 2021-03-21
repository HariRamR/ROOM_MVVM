package com.hari.room_mvvm.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repo {

    private NoteDao noteDao;
    private LiveData<List<NoteModel>> noteList;

    public Repo(Application application){

        NoteDatabase noteDatabase = NoteDatabase.getInstance(application);
        noteDao = noteDatabase.noteDao();
        noteList = noteDao.getNoteData();
    }

    public void insertNote(NoteModel noteModel){
        new InsertNoteAsyncTask(noteDao).execute(noteModel);
    }

    public void updateNote(NoteModel noteModel){
        new UpdateNoteAsyncTask(noteDao).execute(noteModel);
    }

    public void deleteNote(NoteModel noteModel){
        new DeleteNoteAsyncTask(noteDao).execute(noteModel);
    }

    public void deleteAlNotes(){
        new DeleteAllNotesAsyncTask(noteDao).execute();
    }

    public LiveData<List<NoteModel>> getNoteData(){
        return noteList;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private final NoteDao noteDao;

        private InsertNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.insert(noteModels[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private final NoteDao noteDao;

        private UpdateNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.update(noteModels[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private final NoteDao noteDao;

        private DeleteNoteAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteDao.delete(noteModels[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsyncTask extends AsyncTask<Void, Void, Void>{
        private final NoteDao noteDao;

        private DeleteAllNotesAsyncTask(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
