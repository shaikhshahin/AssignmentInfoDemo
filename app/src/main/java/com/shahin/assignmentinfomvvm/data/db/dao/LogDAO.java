package com.shahin.assignmentinfomvvm.data.db.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.shahin.assignmentinfomvvm.data.db.entity.LogClass;

import java.util.List;

/**
 * Created by Shahin on 16/11/2019.
 */

@Dao
public interface LogDAO {

    @Query("SELECT * FROM LogClass")
    List<LogClass> getAll();

    @Query("DELETE FROM LogClass")
    void dropTable();

    @Insert
    void insertAll(LogClass... logs);

    @Delete
    void delete(LogClass log);
}
