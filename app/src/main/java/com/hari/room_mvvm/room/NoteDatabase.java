package com.hari.room_mvvm.room;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.List;

@Database(entities = NoteModel.class, version = 2)
public abstract class NoteDatabase extends RoomDatabase {

    private static NoteDatabase noteDatabase;
    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){

        if (noteDatabase == null){
            noteDatabase = Room.databaseBuilder(context.getApplicationContext(), NoteDatabase.class, "note_database")
                           .fallbackToDestructiveMigration().build();
        }
        return noteDatabase;
    }

    /*static final Migration MIGRATION_1_2 = new Migration(2, 3) {
        @Override
        public void migrate(SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE `note_table` (`id` INTEGER, " + "`title` TEXT, `message` TEXT,  PRIMARY KEY(`id`))");
        }
    };*/
}
