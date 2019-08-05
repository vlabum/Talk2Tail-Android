package com.talk2tail.common.model.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface RoomExampleEntityDao {

    @Insert(onConflict = REPLACE)
    void insert(RoomExampleEntity roomExampleEntity);

    @Insert(onConflict = REPLACE)
    void insert(RoomExampleEntity... roomExampleEntities);

    @Insert(onConflict = REPLACE)
    void insert(List<RoomExampleEntity> roomExampleEntities);

    @Update
    void update(RoomExampleEntity roomExampleEntity);

    @Update
    void update(RoomExampleEntity... roomExampleEntities);

    @Update
    void update(List<RoomExampleEntity> roomExampleEntities);

    @Delete
    void delete(RoomExampleEntity roomExampleEntity);

    @Delete
    void delete(RoomExampleEntity... roomExampleEntities);

    @Delete
    void delete(List<RoomExampleEntity> roomExampleEntities);

}
