package com.hari.room_mvvm.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(NoteModel noteModel);

    @Update
    void update(NoteModel noteModel);

    @Delete
    void delete(NoteModel noteModel);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table")
    LiveData<List<NoteModel>> getNoteData();
}
