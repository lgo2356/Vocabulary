package com.hun.vocabulary.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "vocaList")
public class VocaListEntity {

    @PrimaryKey(autoGenerate = true)
    public long idx;

    public String title;
    public String memo;
    public long vocaId;
}
