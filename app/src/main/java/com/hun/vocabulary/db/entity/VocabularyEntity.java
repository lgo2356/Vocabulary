package com.hun.vocabulary.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.hun.vocabulary.pojo.Word;

import java.util.List;

@Entity(tableName = "vocabulary")
public class VocabularyEntity {

    @PrimaryKey(autoGenerate = true)
    public long idx;

    public String title;
    public String memo;
    public List<Word> words;
}
