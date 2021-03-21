package com.hari.room_mvvm.room;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String message;

    public NoteModel(String title, String message){

        this.title = title;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    public void setId(int id) {
        this.id = id;
    }
}
