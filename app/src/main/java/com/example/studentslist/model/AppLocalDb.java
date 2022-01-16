package com.example.studentslist.model;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities ={Student.class},version = 1)
abstract class AppLocalDbRespository extends RoomDatabase {
    public abstract  StudentDao studentDao();
}

public class AppLocalDb {

    static  public AppLocalDbRespository db=
            Room.databaseBuilder(MyApplication.getContext(),
            AppLocalDbRespository.class,"dbFileName.db")
            .fallbackToDestructiveMigration().build();
}
