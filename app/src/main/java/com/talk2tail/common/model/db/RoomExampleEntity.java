package com.talk2tail.common.model.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import lombok.Data;

@Entity
@Data
public class RoomExampleEntity {

    @PrimaryKey
    private int val;

    private String str;

}
